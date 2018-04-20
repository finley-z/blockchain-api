package com.tt.api.entity;

public class ResultVo {
	public Boolean status;			//成功，失败
	private String errorMsg;		//错误信息
	private String code;			//代码,一般用status处理就可以了
	private Object data;			//返回的数据

	public ResultVo(){
		super();
	}

	public ResultVo(Boolean status) {
		super();
		this.status = status;
	}

	public ResultVo(Boolean status, String errorMsg, String code, Object data) {
		super();
		this.status = status;
		this.errorMsg = errorMsg;
		this.code = code;
		this.data = data;
	}

	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
	
}
