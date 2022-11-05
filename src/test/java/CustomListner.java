import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class CustomListner implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        System.out.println("On test Start : "+result.toString());
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        System.out.println("On test Start : "+context.toString());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        System.out.println("On test success : "+result.toString());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
        System.out.println("On test skipped : "+result.toString());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        System.out.println("On test Fail : "+result.toString());
    }
}
