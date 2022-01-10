package com.myalc.mappingengine.controllers;

import java.util.ArrayList;
import java.util.List;

import com.myalc.mappingengine.model.Config;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping(path = "/configs")
	public String getConfigs4Index(Model model) {
        model.addAttribute("configs", createList());
        return "index";
	}

    private List<Config> createList() {
        // TODO: get configuration from engine app
        List<Config> l = new ArrayList<>(); 
        Config c = new Config();
        c.setConfigName("mapping.sensor.voltage");
        c.setSrcSchema("ewogICJ0eXBlIjogIm9iamVjdCIsCiAgImFkZGl0aW9uYWxQcm9wZXJ0aWVzIjpmYWxzZSwKICAicmVxdWlyZWQiOiBbInJlY2VpdmVyVGFnIiwgInNlbnNvckRhdGEiXSwKICAgInByb3BlcnRpZXMiOnsKICAgICAgInJlY2VpdmVyVGFnIjp7CiAgICAgICAgICJ0eXBlIjoic3RyaW5nIiwKCQkgIm1heExlbmd0aCI6IDEwMCwKCQkgIm1pbkxlbmd0aCI6IDEKICAgICAgfSwKICAgICAgInNlbnNvckRhdGEiOnsKICAgICAgICAgInR5cGUiOiJvYmplY3QiLAogICAgICAgICAiYWRkaXRpb25hbFByb3BlcnRpZXMiOmZhbHNlLAogICAgICAgICAicmVxdWlyZWQiOiBbImlkIiwgInR5cGUiLCAidmFsdWUiLCAic2FtcGxlVHNVdGNNaWxsaXMiXSwKICAgICAgICAgInByb3BlcnRpZXMiOnsKICAgICAgICAgICAgImlkIjp7CiAgICAgICAgICAgICAgICJ0eXBlIjoic3RyaW5nIiwKICAgICAgICAgICAgICAgIm1heExlbmd0aCI6IDEwMCwKICAgICAgICAgICAgICAgIm1pbkxlbmd0aCI6IDEKICAgICAgICAgICAgfSwKICAgICAgICAgICAgInR5cGUiOnsKICAgICAgICAgICAgICAgInR5cGUiOiJzdHJpbmciLAogICAgICAgICAgICAgICAiZW51bSI6IFsidm9sdGFnZSJdCiAgICAgICAgICAgIH0sCiAgICAgICAgICAgICJ2YWx1ZSI6ewogICAgICAgICAgICAgICAidHlwZSI6InN0cmluZyIKICAgICAgICAgICAgfSwKICAgICAgICAgICAgInNhbXBsZVRzVXRjTWlsbGlzIjp7CiAgICAgICAgICAgICAgICJ0eXBlIjoic3RyaW5nIgogICAgICAgICAgICB9CiAgICAgICAgIH0gICAgICAgICAgIAogICAgICB9CiAgIH0KfQ==");
        c.setDstSchema("ewogICJ0eXBlIjogIm9iamVjdCIsCiAgImFkZGl0aW9uYWxQcm9wZXJ0aWVzIjpmYWxzZSwKICAicmVxdWlyZWQiOiBbInRhZyIsICJzZW5zb3JJZCIsICJzZW5zb3JUeXBlIiwgInZhbHVlIiwgInNhbXBsZVRzVXRjTWlsbGlzIiwgInNhbXBsZVRzVXRjSXNvIl0sCiAgICJwcm9wZXJ0aWVzIjp7CiAgICAgICJ0YWciOnsKICAgICAgICAgInR5cGUiOiJzdHJpbmciLAoJCSAibWF4TGVuZ3RoIjogMTAwLAoJCSAibWluTGVuZ3RoIjogMQogICAgICB9LAoJICAic2Vuc29ySWQiOnsKICAgICAgICAgInR5cGUiOiJzdHJpbmciLAoJCSAibWF4TGVuZ3RoIjogMTAwLAoJCSAibWluTGVuZ3RoIjogMQogICAgICB9LAoJICAic2Vuc29yVHlwZSI6ewogICAgICAgICAidHlwZSI6InN0cmluZyIsCgkJICJlbnVtIjogWyJ2b2x0YWdlIiwgInRlbXBlcmF0dXJlIiwgImh1bWlkaXR5Il0KICAgICAgfSwKICAgICAgInZhbHVlIjp7CgkJICJ0eXBlIjoic3RyaW5nIgoJICB9LAogICAgICAic2FtcGxlVHNVdGNNaWxsaXMiOnsKCQkgInR5cGUiOiJzdHJpbmciCgkgIH0sCiAgICAgICJzYW1wbGVUc1V0Y0lzbyI6ewoJCSAidHlwZSI6InN0cmluZyIsCgkJICJwYXR0ZXJuIjogIl5bMC05XXs0fS0oKDBbMTM1NzhdfDFbMDJdKS0oMFsxLTldfFsxMl1bMC05XXwzWzAxXSl8KDBbNDY5XXwxMSktKDBbMS05XXxbMTJdWzAtOV18MzApfCgwMiktKDBbMS05XXxbMTJdWzAtOV0pKSAoMFswLTldfDFbMC05XXwyWzAtM10pOigwWzAtOV18WzEtNV1bMC05XSk6KDBbMC05XXxbMS01XVswLTldKVxcLlswLTldezN9WiQiCgkgIH0KICAgfQp9");
        c.setMapping("ewoJInRhZyI6ICJ7e3JlY2VpdmVyVGFnfX0iLAoJInNlbnNvcklkIjogInt7c2Vuc29yRGF0YS9pZH19IiwKCSJzZW5zb3JUeXBlIjogInt7c2Vuc29yRGF0YS90eXBlfX0iLAoJInZhbHVlIjogInt7c2Vuc29yRGF0YS92YWx1ZX19IiwKCSJzYW1wbGVUc1V0Y01pbGxpcyI6ICJ7e3NlbnNvckRhdGEvc2FtcGxlVHNVdGNNaWxsaXN9fSIsCgkic2FtcGxlVHNVdGNJc28iOiAie3sjZnVuY19lcG9jaE1pbGxpczJpc29EdH19e3tzZW5zb3JEYXRhL3NhbXBsZVRzVXRjTWlsbGlzfX17ey9mdW5jX2Vwb2NoTWlsbGlzMmlzb0R0fX0iCn0=");
        c.setTransform(true);
        c.setDstTopic("engine.mapper.outbound.sensor");
        c.setResult2kafka(true);
        c.setValidateDst(true);
        c.setValidateDst(true);
        l.add(c);

        c = new Config();
        c.setConfigName("mapping.sensor.temperature");
        c.setSrcSchema("");
        c.setDstSchema("");
        c.setMapping("ewogICAgIm15RkEiOiAie3tteUZpZWxkQX19IiwKICAgICJteUZCIjogInt7bXlGaWVsZEJ9fSIsCiAgICAiYSI6ICJ7e3QvYX19IiwKICAgICJiIjogInt7dC9ifX0iCn0=");
        c.setTransform(true);
        c.setDstTopic("engine.mapper.outbound.sensor");
        c.setResult2kafka(false);
        c.setValidateDst(false);
        c.setValidateDst(false);
        l.add(c);

        return l;
    }
}
