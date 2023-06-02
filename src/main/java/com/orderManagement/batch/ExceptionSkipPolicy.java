package com.orderManagement.batch;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;

public class ExceptionSkipPolicy implements SkipPolicy {

	@Override
	public boolean shouldSkip(Throwable throwable, int skipCount) throws SkipLimitExceededException {
		// TODO Auto-generated method stub
		return throwable instanceof Exception;
	}

}
