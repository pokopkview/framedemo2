package reco.frame.demo.NetWork;

/**
 * Created by UPC on 2016/6/27.
 */
public class RequestInterface {
    public interface ISuccessRequest {
        void OnSuccess(Object object);
    }

    public interface IFaileRequest {
        void OnFailed(Object o);
    }
}
