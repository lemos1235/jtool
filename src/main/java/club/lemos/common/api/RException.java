package club.lemos.common.api;

public class RException extends ApiException {

    /**
     * 错误码
     */
    private IResultCode resultCode;

    public RException(String message) {
        super(message);
    }

    public RException(IResultCode resultCode) {
        super(resultCode.getMsg());
        this.resultCode = resultCode;
    }

    public IResultCode getResultCode() {
        return resultCode;
    }
}
