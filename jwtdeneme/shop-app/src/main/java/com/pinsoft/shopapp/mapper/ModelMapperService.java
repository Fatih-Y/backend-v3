package com.pinsoft.shopapp.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public interface ModelMapperService {
    ModelMapper forResponse();
}
