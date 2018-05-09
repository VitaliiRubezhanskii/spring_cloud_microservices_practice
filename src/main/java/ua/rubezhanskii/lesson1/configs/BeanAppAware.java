package ua.rubezhanskii.lesson1.configs;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

public class BeanAppAware implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
      String[] names= applicationContext.getBeanDefinitionNames();
          for (String name:names) {
              System.out.println(name);
   }


    }
}
