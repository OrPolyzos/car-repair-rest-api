package com.webservices.rest.carrepairrest.converters;

public interface Mappable<E, M> {

     E convertToEntity(M model);

     M convertToModel(E entity);
}
