package home.application.teai_pracadomowatydzien9.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectService {

    private long time;

    @Before("@annotation(home.application.teai_pracadomowatydzien9.aspect.AspectServiceAnnotation)")
    public void counterStart(){
        time = System.currentTimeMillis();
    }

    @After("@annotation(home.application.teai_pracadomowatydzien9.aspect.AspectServiceAnnotation)")
    public void counterEnd(){
        System.out.println(System.currentTimeMillis() - time);
    }


}
