package com.kcj.user.reponseUtil;

import com.kcj.user.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;


/**
 * 标记为@Deprecated方法为兼容qitoon代码，不建议使用
 * @param <T>返回的数据类型
 */
public class BaseResponse<T> implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(User.class);
    public static final int NO_LOGIN = -1;

    public static final int SUCCESS = 0;

    public static final int FAIL = 1;

    public static final int NO_PERMISSION = 2;

    public static <E> BaseResponse<E> successWithData(E data) {
        BaseResponse<E> res = new BaseResponse<E>(data);
        res.setCode(String.valueOf(SUCCESS));
        res.setIsSuccess(true);
        res.setErrorCode(String.valueOf(SUCCESS));
        res.setMessage("ok!");
        return res;
    }

    public static <E> BaseResponse<E> successWithoutData() {
        BaseResponse<E> res = new BaseResponse<E>();
        res.setCode(String.valueOf(SUCCESS));
        res.setIsSuccess(true);
        res.setErrorCode(String.valueOf(SUCCESS));
        res.setMessage("ok!");
        return res;
    }

    public static <E> BaseResponse<E> error(ErrorDefinition def) {
        BaseResponse<E> res = new BaseResponse<E>();
        res.setCode(String.valueOf(FAIL));
        res.setIsSuccess(false);
        res.setErrorCode(def.getErrorCode());
        res.setMessage(def.getErrorMsg());
        return res;
    }

    public static <E> BaseResponse<E> error(Throwable e) {
        BaseResponse<E> res = new BaseResponse<E>();
        res.setCode(String.valueOf(FAIL));
        res.setIsSuccess(false);
        res.setErrorCode(String.valueOf(FAIL));
        res.setMessage(e.getMessage());
        logger.error(e.getMessage(), e);
        return res;
    }

    public static <E> BaseResponse<E> error(ErrorDefinition def, String msg) {
        BaseResponse<E> res = new BaseResponse<E>();
        res.setCode(String.valueOf(FAIL));
        res.setIsSuccess(false);
        res.setErrorCode(def.getErrorCode());
        res.setMessage(msg);
        return res;
    }

    public static <E> BaseResponse<E> errorWithData(E data) {
        BaseResponse<E> res = new BaseResponse<E>(data);
        res.setCode(String.valueOf(FAIL));
        res.setIsSuccess(false);
        res.setErrorCode(String.valueOf(FAIL));
        if(null != data) {
            res.setMessage(data.toString());
        }
        return res;
    }

    public BaseResponse(T data) {
        this(String.valueOf(SUCCESS), "成功", data);
        this.isSuccess = true;
    }

    public BaseResponse(String code, String message) {
        this(code, message, null);
        if(String.valueOf(SUCCESS).equals(code)) {
            this.isSuccess = true;
        }
    }

    public BaseResponse(String code, String message, T data) {
        if(String.valueOf(SUCCESS).equals(code)) {
            this.isSuccess = true;
        }
        this.setCode(code);
        this.message = message;
        this.data = data;
    }

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<T>(data);
    }

    public static <T> BaseResponse<T> fail(String code, String message, T data) {
        return new BaseResponse<T>(code, message, data);
    }

    public static <T> BaseResponse<T> fail(String code, String message) {
        return new BaseResponse<T>(code, message);
    }

    public static <T> BaseResponse<T> fail(Integer code, String message) {
        return new BaseResponse<T>(code.toString(), message);
    }

    private static final long serialVersionUID = 1L;
    private String code;
    private Boolean isSuccess;
    private String message;
    private String errorCode;
    private T data;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BaseResponse() {
    }

    /**
     * @return the isSuccess
     */
    public Boolean getIsSuccess() {
        return isSuccess;
    }

    /**
     * @param isSuccess
     *            the isSuccess to set
     */
    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
