package com.bkap.convert;

import com.bkap.dto.BlogDto;
import com.bkap.entities.Blog;
import com.bkap.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BlogConvert {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    public BlogDto convertToDto(Blog blogEntity) {
        BlogDto blogDto = modelMapper.map(blogEntity,BlogDto.class);
        blogDto.setAuthorId(blogEntity.getUser().getId());
        return blogDto;
    }

    public Blog convertToEntity(BlogDto blogDto){
        Blog blog = modelMapper.map(blogDto,Blog.class);
        blog.setUser(userRepository.findById(blogDto.getAuthorId()).get());
        return blog;
    }

    public Blog convertToEntity(BlogDto blogDto, Blog blogOld ) {
        Blog blogNew = modelMapper.map(blogDto,Blog.class);
        blogOld.setSlug(blogNew.getSlug());
        blogOld.setTitle(blogNew.getTitle());
        blogOld.setShortDescription(blogNew.getShortDescription());
        blogOld.setDetailDescription(blogNew.getDetailDescription());
        blogOld.setCreateAt(blogNew.getCreateAt());
        blogOld.setUpdateAt(blogNew.getUpdateAt());
        blogOld.setUser(userRepository.findById(blogDto.getId()).get());
        return blogOld;
    }
}
