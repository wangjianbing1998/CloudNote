package com.wjb.controller;

import com.wjb.dao.HistoryItemDao;
import com.wjb.model.HistoryItemEntity;
import com.wjb.utils.KVEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HistoryItemController {
    //映射一个action
    HistoryItemDao HistoryItemDao = new HistoryItemDao();
    @RequestMapping(value = "/findHistoryItems")
    @ResponseBody//表示直接输出返回内容，不进行jsp或html跳转，本例是为了写接口，这里直接返回json
    public List<HistoryItemEntity> getUser(@RequestParam int userId) {
        List<KVEntity> parameter=new ArrayList<KVEntity>();
        parameter.add(new KVEntity("userId", (userId)));
        return HistoryItemDao.query("select history from HistoryItemEntity history,NoteEntity note where history.notes_id =note.id and note.userId= :userId  order by id desc ",parameter);
//        return HistoryItemDao.query("select history from HistoryItemEntity history where history.notes_id in(select id from NoteEntity where userId= :userId)  order by id desc ",parameter);
    }

    @ResponseBody
    @RequestMapping(value = "/insertHistoryItems")
    public String doInsert(HttpServletRequest req,@RequestParam int notes_id,
                           @RequestParam String update_time, @RequestParam int update_type){

        String before_title="";
        String after_title="";
        String before_content="";
        String after_content="";

        switch (update_type) {
            case 1:
                // 增加笔记
                after_title = req.getParameter("after_title");
                after_content = req.getParameter("after_content");
                break;

            case 2:
                // 删除笔记
                before_title = req.getParameter("before_title");
                before_content = req.getParameter("before_content");
                break;

            case 3:
                // 查询笔记
                before_title = req.getParameter("before_title");
                before_content = req.getParameter("before_content");
                after_title = req.getParameter("after_title");
                after_content = req.getParameter("after_content");
                break;
            case 4:
                before_title = req.getParameter("before_title");
                before_content = req.getParameter("before_content");
                after_title = req.getParameter("after_title");
                after_content = req.getParameter("after_content");
                // 修改笔记
                break;
            default:

                break;
        }

        boolean result = HistoryItemDao.insert(new HistoryItemEntity(0, notes_id, update_time,update_type,before_title,before_content,after_title,after_content));
        return String.valueOf(result);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteHistoryItems")
    public String doDelete(@RequestParam int history_id){

        int result = HistoryItemDao.delete(history_id);
        if(result>0)
            return "true";
        else
            return "false";
    }

    @ResponseBody
    @RequestMapping(value = "/deleteAllHistoryItems")
    public String doAllDelete(@RequestParam int user_id){

        int result = HistoryItemDao.deleteAll(user_id);
        if(result>0)
            return "true";
        else
            return "false";
    }





    @ResponseBody
    @RequestMapping(value = "/searchHistoryItems")
    public List<HistoryItemEntity> doSearchHistoryItems(@RequestParam int userId,@RequestParam String key_words){
        List<KVEntity> parameter=new ArrayList<KVEntity>();
        parameter.add(new KVEntity("userId", userId));
//        parameter.add(new KVEntity("key_words", key_words));

        List<HistoryItemEntity> result = HistoryItemDao.query("from HistoryItemEntity where notes_id in (select id from NoteEntity where userId= :userId) and (before_title like '%"+key_words+"%' or  before_content like '%"+key_words+"%' or  after_title like '%"+key_words+"%' or  after_content like '%"+key_words+"%') order by id desc ",parameter);
        return result;
    }
}
