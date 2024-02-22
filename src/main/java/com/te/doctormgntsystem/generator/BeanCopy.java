package com.te.doctormgntsystem.generator;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

public class BeanCopy {

	public static <E, T> List<T> copy(List<E> sourceList, Class<T> targetClass) {
		return sourceList.stream().map(x -> {
			T newInstance = null;
			try {
				newInstance = targetClass.getDeclaredConstructor().newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
			BeanUtils.copyProperties(x, newInstance);
			return newInstance;
		}).collect(Collectors.toList());
	}
}
