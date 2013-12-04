package edu.sjsu.cmpe.kidsontrack.util;

import java.util.concurrent.atomic.AtomicLong;

public final class SequenceGenerator {

	
	private static final AtomicLong sequenceTeacherId = new AtomicLong();
	private static final AtomicLong sequenceStudentId = new AtomicLong();
	private static final AtomicLong sequenceCourseId = new AtomicLong();
	private static final AtomicLong sequenceScoreId = new AtomicLong();

	private SequenceGenerator() {
	}

	
	public static long nextTeacherId() {
		return sequenceTeacherId.incrementAndGet();
	}

	public static long nextStudentId() {
		return sequenceStudentId.incrementAndGet();
	}

	public static long nextScoreId() {
		return sequenceScoreId.incrementAndGet();
	}
	
	public static long nextCourseId() {
		return sequenceCourseId.incrementAndGet();
	}
}