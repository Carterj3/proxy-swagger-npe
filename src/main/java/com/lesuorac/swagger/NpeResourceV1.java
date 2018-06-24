package com.lesuorac.swagger;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping(path = "v1/npe")
public interface NpeResourceV1 {

	@RequestMapping(path = "/", method = { RequestMethod.GET })
	void asyncCausesNpe(@RequestParam(name = "B") boolean b);
}
