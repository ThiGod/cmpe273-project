package edu.sjsu.cmpe.kidsontrack.util;

import java.util.concurrent.atomic.AtomicLong;

public final class SequenceGenerator {
	private static final AtomicLong sequenceISBN = new AtomicLong();
	private static final AtomicLong sequenceReviewId = new AtomicLong();
	private static final AtomicLong sequenceAuthorId = new AtomicLong();
	
	private static final AtomicLong sequenceTeacherId = new AtomicLong();
	private static final AtomicLong sequenceStudentId = new AtomicLong();
	private static final AtomicLong sequenceCourseId = new AtomicLong();

	private SequenceGenerator() {
	}

	public static long nextISBN() {
		return sequenceISBN.incrementAndGet();
	}

	public static long nextReviewId() {
		return sequenceReviewId.incrementAndGet();
	}

	public static long nextAuthorId() {
		return sequenceAuthorId.incrementAndGet();
	}
	
	public static long nextTeacherId() {
		return sequenceTeacherId.incrementAndGet();
	}

	public static long nextStudentId() {
		return sequenceStudentId.incrementAndGet();
	}

	public static long nextCourseId() {
		return sequenceCourseId.incrementAndGet();
	}
}