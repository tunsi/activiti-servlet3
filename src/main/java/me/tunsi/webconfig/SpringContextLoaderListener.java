package me.tunsi.webconfig;

import javax.servlet.annotation.WebListener;

import org.springframework.web.context.ContextLoaderListener;

@WebListener("This is spring context loader.")
public class SpringContextLoaderListener extends ContextLoaderListener {

}
