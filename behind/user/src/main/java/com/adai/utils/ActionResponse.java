package com.adai.utils;

/**
 * description: 服务之间交互统一响应
 * <p>
 * <pre>
 *     返回划分为2部分，分别是头部和实体信息，头部返回状态为200,则从Body里面取数据，如果头部返回异常状态码，则从头部取出错误信息
 *     </pre>
 */
public class ActionResponse<T> {

    /**
     * 返回的头部信息
     */
    private Head head = new Head();

    /**
     * 返回主体信息
     */
    private Body body = new Body();

    //只返回head的code和description  没有返回数据
    public static <T> ActionResponse<T> success() {
        return new ActionResponse<T>(RespBasicCode.SUCCESS);
    }

    //只返回head的code和description  并且返回数据result
    public static <T> ActionResponse<T> success(T result) {
        return new ActionResponse<T>(RespBasicCode.SUCCESS, result);
    }

    //只返回head的code和description  没有返回数据
    public static <T> ActionResponse<T> fail(RespBasicCode respCode) {
        return new ActionResponse<T>(respCode);
    }

    //只返回head的code和description  并且返回数据result
    public static <T> ActionResponse<T> fail(RespBasicCode respCode, T result) {
        return new ActionResponse<T>(respCode, result);
    }


    //只设置 head的相关信息  不设置 body相关信息
    private ActionResponse(RespBasicCode respCode) {
        this.head.setCode(respCode.getResultCode());
        this.head.setResult(respCode.getResultDes());
    }

    //设置Head的值  并且设置result的值
    private ActionResponse(RespBasicCode respCode, T result) {
        this.head.setCode(respCode.getResultCode());
        this.head.setResult(respCode.getResultDes());
        this.body.setResult(result);
    }

    //自定义 head中的信息
    public ActionResponse(RespBasicCode respBasicCode, String msg, T result) {
        this.head.result = msg;
        this.head.code = respBasicCode.getResultCode();
        this.body.data = result;
    }

    //自定义 code  和 description 和 result
    public ActionResponse(String code, String msg, T result) {
        this.head.code = code;
        this.head.result = msg;
        this.body.data = result;
    }

    //自定义 code and description
    public ActionResponse(String code, String msg) {
        this.head.code = code;
        this.head.result = msg;
    }

    /**
     * 返回的实体信息
     */
    private class Body {

        /**
         * 数据域，如果是分页直接为，PageResult即可
         */
        private T data;

        private T getResult() {
            return data;
        }

        private void setResult(T result) {
            this.data = result;
        }
    }

    /**
     * 响应的头部
     */
    private class Head {

        /**
         * 状态码
         */
        private String code;

        /**
         * 状态码对象的结果
         */
        private String result;

        public String getResult() {
            return result;
        }

        private void setResult(String result) {
            this.result = result;
        }

        public String getCode() {
            return code;
        }

        private void setCode(String code) {
            this.code = code;
        }
    }

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public T getBody() {
        return body.getResult();
    }
}
