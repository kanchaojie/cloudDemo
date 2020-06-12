package com.kcj.studyJDK8;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;
import ma.glasnost.orika.metadata.TypeFactory;

import java.util.ArrayList;
import java.util.List;

public final class BeanUtils {
	private static MapperFacade a = new DefaultMapperFactory.Builder().build().getMapperFacade();

	public static <S, D> D map(S paramS, Class<D> paramClass) {
		return a.map(paramS, paramClass);
	}

	public static <S, D> D map(S paramS, Type<S> paramType, Type<D> paramType1) {
		return a.map(paramS, paramType, paramType1);
	}

	public static <S, D> List<D> mapList(Iterable<S> paramIterable, Class<S> paramClass, Class<D> paramClass1) {
		if(null == paramIterable) {
			return new ArrayList<D>();
		}
		return a.mapAsList(paramIterable, TypeFactory.valueOf(paramClass), TypeFactory.valueOf(paramClass1));
	}

	public static <S, D> List<D> mapList(Iterable<S> paramIterable, Type<S> paramType, Type<D> paramType1) {
		if(null == paramIterable) {
			return new ArrayList<D>();
		}
		return a.mapAsList(paramIterable, paramType, paramType1);
	}

	public static <S, D> D[] mapArray(D[] paramArrayOfD, S[] paramArrayOfS, Class<D> paramClass) {
		return a.mapAsArray(paramArrayOfD, paramArrayOfS, paramClass);
	}

	public static <S, D> D[] mapArray(D[] paramArrayOfD, S[] paramArrayOfS, Type<S> paramType, Type<D> paramType1) {
		return a.mapAsArray(paramArrayOfD, paramArrayOfS, paramType, paramType1);
	}

	public static <E> Type<E> getType(Class<E> paramClass) {
		return TypeFactory.valueOf(paramClass);
	}
}
