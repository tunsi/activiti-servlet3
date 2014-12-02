package me.tunsi.webconfig;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import org.springframework.web.filter.CharacterEncodingFilter;

@WebFilter(filterName = "encodingFilter", initParams = { @WebInitParam(name = "encoding", value = "UTF-8") }, urlPatterns = { "/*" })
public class SpringCharacterEncodingFilter extends CharacterEncodingFilter {

}
