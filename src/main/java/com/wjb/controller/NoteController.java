package com.wjb.controller;

import com.wjb.dao.NoteDao;


import com.wjb.model.NoteEntity;
import com.wjb.utils.KVEntity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")//Contoller下所有接口统一入口
public class NoteController {
    //映射一个action
    NoteDao noteDao = new NoteDao();
    @RequestMapping(value = "/findNotes")
    @ResponseBody//表示直接输出返回内容，不进行jsp或html跳转，本例是为了写接口，这里直接返回json
    public List<NoteEntity> getUser(@RequestParam int userId) {
        List<KVEntity> parameter=new ArrayList<KVEntity>();
        parameter.add(new KVEntity("userId", (userId)));
        return noteDao.query("from NoteEntity where userId= :userId order by id desc ",parameter);
    }

    @ResponseBody
    @RequestMapping(value = "/insertNotes")
    public String doInsert(@RequestParam String title,@RequestParam String content,@RequestParam String creating_date,@RequestParam int user_id){
        List<KVEntity> parameter=new ArrayList<KVEntity>();
        parameter.add(new KVEntity("title", title));

        List<NoteEntity> list = noteDao.query("from NoteEntity where title= :title", parameter);

        if(list.size()>0){
            return "0";
        }

        boolean result = noteDao.insert(new NoteEntity(0, title, content,creating_date,user_id,0));
        if(result){
            return "1";
        }
        else return "-1";
    }

    @ResponseBody
    @RequestMapping(value = "/deleteNotes")
    public String doDelete(@RequestParam int note_id){

        List<KVEntity> parameter=new ArrayList<KVEntity>();
        parameter.add(new KVEntity("note_id", (note_id)));
        List<NoteEntity> list = noteDao.query("from NoteEntity where id= :note_id", parameter);
        int result=0;
        if(list.size()>0){
            result = noteDao.delete(note_id);
        }
        if(result>0)
            return "true";
        else
            return "false";
    }


    @ResponseBody
    @RequestMapping(value = "/updateNotes")
    public String doUpdate(@RequestParam int note_id,@RequestParam String title,@RequestParam String content){
        List<KVEntity> parameter=new ArrayList<KVEntity>();
        parameter.add(new KVEntity("note_id", note_id));
        parameter.add(new KVEntity("title", title));
        parameter.add(new KVEntity("content", content));
        int result = noteDao.update("update NoteEntity note set note.title= :title ,note.content= :content where note.id= :note_id",parameter);
        if(result>0)
            return "true";
        else
            return "false";
    }

    @ResponseBody
    @RequestMapping(value = "/collectNotes")
    public String doCollect(@RequestParam int note_id){
        List<KVEntity> parameter=new ArrayList<KVEntity>();
        parameter.add(new KVEntity("note_id", note_id));
        int result = noteDao.update("update NoteEntity note set note.isCollected=1 where note.id= :note_id",parameter);
        if(result>0)
            return "true";
        else
            return "false";
    }

    @ResponseBody
    @RequestMapping(value = "/findCollectedNotes")
    public List<NoteEntity> doFindCollectedNotes(@RequestParam int userId){
        List<KVEntity> parameter=new ArrayList<KVEntity>();
        parameter.add(new KVEntity("userId", userId));
        List<NoteEntity> result = noteDao.query("from NoteEntity where userId= :userId and isCollected=1 order by id desc ",parameter);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCollectedNotes")
    public String doDeleteCollectedNotes(@RequestParam int note_id){
        List<KVEntity> parameter=new ArrayList<KVEntity>();
        parameter.add(new KVEntity("note_id", note_id));
        int result = noteDao.update("update NoteEntity note set note.isCollected=0 where note.id= :note_id",parameter);
        if(result>0)
            return "true";
        else
            return "false";
    }


    @ResponseBody
    @RequestMapping(value = "/searchCollectedNotes")
    public List<NoteEntity> doSearchCollectedNotes(@RequestParam int userId,@RequestParam String key_words){
        List<KVEntity> parameter=new ArrayList<KVEntity>();
        parameter.add(new KVEntity("userId", userId));
//        parameter.add(new KVEntity("key_words", key_words));

        List<NoteEntity> result = noteDao.query("from NoteEntity where userId= :userId and isCollected=1 and (title like '%"+key_words+"%' or  content like '%"+key_words+"%') order by id desc ",parameter);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/searchNotes")
    public List<NoteEntity> doSearchNotes(@RequestParam int userId,@RequestParam String key_words){
        List<KVEntity> parameter=new ArrayList<KVEntity>();
        parameter.add(new KVEntity("userId", userId));
//        parameter.add(new KVEntity("key_words", key_words));

        List<NoteEntity> result = noteDao.query("from NoteEntity where userId= :userId and (title like '%"+key_words+"%' or  content like '%"+key_words+"%') order by id desc ",parameter);
        return result;
    }


















}
