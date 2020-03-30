package home.application.teai_pracadomowatydzien9.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectLoad {

    private long time;

    @Before("@annotation(AspectServiceLoad)")
    public void counterStart(){
        time = System.currentTimeMillis();
    }

    @After("@annotation(AspectServiceLoad)")
    public void counterEnd(){
        System.out.format("Load time %d\n", (System.currentTimeMillis() - time));
    }


}
