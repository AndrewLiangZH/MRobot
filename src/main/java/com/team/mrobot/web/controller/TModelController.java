package com.team.mrobot.web.controller;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.team.mrobot.web.domain.TModel;
import com.team.mrobot.web.service.TModelService;
import com.team.mrobot.web.util.ConstraintViolationExceptionHandler;
import com.team.mrobot.web.util.PushClientUtil;
import com.team.mrobot.web.util.PushTest;
import com.team.mrobot.web.vo.Response;
import com.team.mrobot.web.vo.TClient;

import javax.validation.ConstraintViolationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Project: TaaS
 * Author: AndrewLiang
 * Date: 2017/9/23
 * Description: Controller of TModel
 */

@Controller
@RequestMapping("/tmodel")
public class TModelController {

    @Autowired
    private TModelService tModelService;

    /**
     * List and paging the TModels
     *
     * @param async
     * @param pageIndex
     * @param pageSize
     * @param name
     * @param model
     * @return
     */
    @GetMapping
    public ModelAndView listTModels(
            @RequestParam(value = "async", required = false) boolean async,
            @RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "name", required = false, defaultValue = "") String name,
            Model model) {
        Pageable pageable = new PageRequest(pageIndex, pageSize);
        Page<TModel> page = tModelService.listModelsByNameLike(name, pageable);
        List<TModel> tModels = page.getContent();
        model.addAttribute("page", page);
        model.addAttribute("tModels", tModels);

        return new ModelAndView(async == true ? "backstage/tmodel/list :: #rightMainContainerReplace" : "backstage/tmodel/list", "tm_Model", model);
    }

    /**
     * Get the page of form creating
     *
     * @param model
     * @return
     */
    @GetMapping("/add")
    public ModelAndView createForm(Model model) {
        model.addAttribute("tModel", new TModel(null, null, null));
        model.addAttribute("title", "Create Test Model");

        return new ModelAndView("backstage/tmodel/add", "tm_Model", model);
    }

    /**
     * Get the page of form modifying
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("edit/{id}")
    public ModelAndView modifyForm(@PathVariable("id") Long id, Model model) {
        TModel tModel = tModelService.getModelById(id);
        model.addAttribute("tModel", tModel);
        return new ModelAndView("backstage/tmodel/edit", "tm_Model", model);
    }

    /**
     * Save or update the TModel
     *
     * @param tModel
     * @return
     */
    @PostMapping
    public ResponseEntity<Response> saveOrUpdateTModel(TModel tModel) {
        try {
            tModelService.saveOrUpdate(tModel);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
        }
        return ResponseEntity.ok().body(new Response(true, "Success", tModel));
    }

    /**
     * Delete the TModel by id
     *
     * @param id
     * @param model
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteTModel(@PathVariable("id") Long id, Model model) {
        try {
            tModelService.removeModel(id);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, e.getMessage()));
        }
        return ResponseEntity.ok().body(new Response(true, "Success"));
    }

    /**
     * Push the delete operation to the client
     *
     * @return
     */
    @GetMapping("/client/delete/{tName}")
    public ResponseEntity<Response> clientDelete(@PathVariable("tName") String tName) {
        String operating = "DELETE";
        String url = "http://192.168.1.10:8080/mode/";
        try {
            PushTest.testSendPush(operating,url,tName,"");
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
        System.out.println("sucessful!!");
        return ResponseEntity.ok().body(new Response(true, "Success"));
    }

    /**
     * Push the update operation to the client
     *
     * @return
     */
    @GetMapping("/client/update/{tName}")
    public ResponseEntity<Response> clientUpdate(@PathVariable("tName") String tName) {
        String operating = "APPLY";
        String url = "http://192.168.1.10:8080/mode/";
        try {
            PushTest.testSendPush(operating,url,tName,"");
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
        System.out.println("sucessful!!");
        return ResponseEntity.ok().body(new Response(true, "Success"));
    }

    /**
     * Push the upload operation to the client
     *
     * @return
     */
    @GetMapping("/client/upload/{tName}")
    public ResponseEntity<Response> clientUpload(@PathVariable("tName") String tName) {
        String operating = "UPLOAD";
        String url = "http://192.168.1.10:8080/mode/";
        try {
            String label_file ="D:/IDEA Project/Experiments/TaaS-test/src/main/resources/static/mode/"+tName+"_labels.txt";
            String label_conetent =txt2String(new File(label_file));
            System.out.println(label_conetent);
            PushTest.testSendPush(operating, url, tName,label_conetent);
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
        System.out.println("sucessful!!");
        return ResponseEntity.ok().body(new Response(true, "Success"));
    }

    /**
     * Push the execute operation to the client
     *
     * @return
     */
    @GetMapping("/client/execute")
    public ResponseEntity<Response> clientRun() {
        try {
            PushClientUtil.testSendPush(new TClient("EXECUTE"));
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(new Response(true, "Success"));
    }

    public static String txt2String(File file) {
        StringBuilder result = new StringBuilder();


        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String s = null;
            while ((s=br.readLine())!=null){

                result.append(s+System.lineSeparator());
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

}
