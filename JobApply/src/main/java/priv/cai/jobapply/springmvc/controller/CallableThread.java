package priv.cai.jobapply.springmvc.controller;

import java.util.concurrent.Callable;

import priv.cai.jobapply.dataload.spider.Spider;  

public class CallableThread implements Callable<String> {  
	
    private String str;
    
    public CallableThread(String str){  
        this.str=str;  
    }  

    public String call() throws Exception {  
        return Spider.getContext(str);  
    }
    
    
}
