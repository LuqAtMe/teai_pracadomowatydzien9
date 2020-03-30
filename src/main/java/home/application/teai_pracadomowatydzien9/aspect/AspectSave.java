package home.application.teai_pracadomowatydzien9.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectSave {

    private long time;

    @Before("@annotation(AspectServiceSave)")
    public void counterStart(){
        time = System.currentTimeMillis();
    }

    @After("@annotation(AspectServiceSave)")
    public void counterEnd(){
        System.out.format("Save time %d\n", (System.currentTimeMillis() - time));
    }


}
