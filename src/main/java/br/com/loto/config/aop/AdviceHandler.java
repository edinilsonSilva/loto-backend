package br.com.loto.config.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Aspect
@Slf4j
@Component
public class AdviceHandler {

	@Before(value = "execution(* br.com.fydu..*.*(..))")
	public void before(JoinPoint joinPoint) {
		if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
			List<String> args = Arrays.asList(joinPoint.getArgs()).stream().map(p -> p != null ? p.toString() : "").collect(Collectors.toList());
			log.info("Class Called {}, Method called {}, parans: {} ", joinPoint.getSignature().getDeclaringType().getSimpleName(), joinPoint.getSignature().getName(), args);
		}
	}

}
