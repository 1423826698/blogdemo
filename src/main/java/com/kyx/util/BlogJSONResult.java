package com.kyx.util;

/**
 * @Description: 自定义响应数据结构
 *
 */
public class BlogJSONResult {
    //响应状态服务
    private Integer status;

    //响应消息
    private String msg;

    //响应中的数据
    private Object data;

    private String ok;

    public static BlogJSONResult build(Integer status,String msg,Object data){
        return new BlogJSONResult(status,msg,data);
    }

    public static BlogJSONResult ok(Object data){
        return new BlogJSONResult(data);
    }
    public static BlogJSONResult ok(){
        return new BlogJSONResult(null);
    }
    public static BlogJSONResult errorTokenMsg(String msg){return new BlogJSONResult(502,msg,null);}
    public BlogJSONResult(Integer status,String msg,Object data){
        this.status =status;
        this.msg =msg;
        this.data =data;
    }
    public BlogJSONResult(Object data){
        this.status =200;
        this.msg ="ok";
        this.data =data;
    }
    public Boolean isOK() {
        return this.status == 200;
    }
    public Integer getStatus(){
        return status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }

    public void setStatus(Integer status){
        this.status = status;
    }
    public String getMsg(){
        return msg;
    }

}
