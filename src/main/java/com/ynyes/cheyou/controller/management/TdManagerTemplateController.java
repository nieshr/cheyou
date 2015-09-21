package com.ynyes.cheyou.controller.management;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ynyes.cheyou.entity.TdFile;
import com.ynyes.cheyou.entity.TdManager;
import com.ynyes.cheyou.entity.TdManagerRole;
import com.ynyes.cheyou.service.TdManagerLogService;
import com.ynyes.cheyou.service.TdManagerRoleService;
import com.ynyes.cheyou.service.TdManagerService;
import com.ynyes.cheyou.util.SiteMagConstant;

/**
 * 后台模板管理
 * 
 * @author Sharon
 */

@Controller
@RequestMapping(value = "/Verwalter/template")
public class TdManagerTemplateController {

    String filepath = SiteMagConstant.templatePath;
    
    @Autowired
    TdManagerLogService tdManagerLogService;

    @Autowired
    TdManagerRoleService tdManagerRoleService;
    
    @Autowired
    TdManagerService tdManagerService;
    
    @RequestMapping(value = "/list")
    public String setting(String __EVENTTARGET, String __EVENTARGUMENT,
            String __VIEWSTATE, String[] listChkTitle, ModelMap map,
            HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }

        //管理员角色
        TdManager tdManager = tdManagerService.findByUsernameAndIsEnableTrue(username);
        TdManagerRole tdManagerRole = null;
        
        if (null != tdManager.getRoleId())
        {
            tdManagerRole = tdManagerRoleService.findOne(tdManager.getRoleId());
        }
        
        if (null != tdManagerRole) {
			map.addAttribute("tdManagerRole", tdManagerRole);
		}
        
        File file = new File(filepath);

        List<TdFile> temList = new ArrayList<TdFile>();

        if (file.isDirectory()) {
            String[] filelist = file.list();

            for (String f : filelist) {
                File rfile = new File(filepath + f);

                if (rfile.isFile()) {
                    TdFile tem = new TdFile();
                    tem.setTitle(rfile.getName());
                    tem.setModifyTime(new Date(rfile.lastModified()));
                    temList.add(tem);
                }
            }
        }

        map.addAttribute("template_list", temList);

        return "/site_mag/template_list";
    }

    @RequestMapping
    public String content(String name, Long status, ModelMap map,
            HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        
        if (null == name || name.isEmpty())
        {
            return "redirect:/Verwalter/template/list";
        }
        
        File file = new File(filepath + name);

        if (!file.isDirectory() && file.exists()) {
            InputStreamReader read = null;
            try {
                read = new InputStreamReader(new FileInputStream(file), "UTF-8");
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            
            BufferedReader bufferedReader = new BufferedReader(read);
            String str = "";
            String lineTxt = null;
            
            try {
                while((lineTxt = bufferedReader.readLine()) != null){
                    str += lineTxt + '\n';
                }
                read.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            
            map.addAttribute("name", file.getName());
            map.addAttribute("content", str);
        }
        map.addAttribute("status", status);

        return "/site_mag/template_edit";
    }
    
    @RequestMapping(value="/save", method=RequestMethod.POST)
    public String save(String name, String content, ModelMap map,
            HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        
        if (null == name || name.isEmpty() || null == content)
        {
            return "redirect:/Verwalter/template/list";
        }
        
        File file = new File(filepath + name);

        if (!file.isDirectory() && file.exists()) {
            
            try {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(content.getBytes());
                fos.flush();
                fos.close();
                
                tdManagerLogService.addLog("edit", "用户修改模板", req);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "redirect:/Verwalter/template?name=" + name + "&status=1";
    }
}
