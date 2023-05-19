package com.bkap.utils.service.impl;
import com.bkap.convert.RoomConvert;
import com.bkap.dto.RoomDto;
import com.bkap.entities.Room;
import com.bkap.exception.DuplicateRecordException;
import com.bkap.exception.NotFoundException;
import com.bkap.filter.BaseFilter;
import com.bkap.repositories.RoomRepository;
import com.bkap.utils.service.IGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.*;
import java.util.*;

@Service
public class RoomService implements IGeneralService<RoomDto, BaseFilter> {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomConvert convert;

    @Override
    public List<RoomDto> findAll() {
        return null;
    }


    @Override
    public Page<RoomDto> paginations(BaseFilter filter) {
        Pageable pageable = null;
        if(filter.getPageSize() == 0){
            pageable = PageRequest.of(0, Integer.MAX_VALUE);
        }
        else {
            pageable = PageRequest.of(filter.getPage(), filter.getPageSize());
        }
        Page<Room> pageRoomEntitys = roomRepository.findAll(new Specification<Room>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Room> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                filter.getColumnFilters().forEach((key,value) -> {
                    if(value == null){
                        value = "";
                    }
                    if(key != null && !value.equals("")) {
                        if(key.equals("categoryId")){
                            predicates.add(criteriaBuilder.equal(root.get("category").get("id"),value));
                        }
                        else if(key.equals("packageId")){
                            predicates.add(criteriaBuilder.equal(root.get("servicePackage").get("id"),value));
                        }
                        else if(key.equals("titleService")){
                            predicates.add(criteriaBuilder.like(root.get("servicePackage").get("id").as(String.class), "%" + value + "%"));
                        }
                        else if(key.equals("userId")){
                            predicates.add(criteriaBuilder.equal(root.get("user").get("id"),value));
                        }
                        else if(key.equals("rateServiceNot")){
                            predicates.add(criteriaBuilder.notEqual(root.get("servicePackage").get("rate"),value));
                        }
                        else if(key.equals("price")){
                            String[] priceRender = value.split("-");
                            float priceFrom = Float.parseFloat(priceRender[1]);
                            float priceTo = Float.parseFloat(priceRender[2]);
                            predicates.add(
                                    criteriaBuilder.between(root.get("price"), priceFrom, priceTo));
                        }
                        else if(key.equals("acreage")) {
                            String[] areaRender = value.split("-");
                            float areaFrom = Float.parseFloat(areaRender[1]);
                            float areaTo = Float.parseFloat(areaRender[2]);
                            predicates.add(
                                    criteriaBuilder.between(root.get("area"), areaFrom, areaTo));
                        }
                        else {
                            if (key.endsWith("Ext")) {
                                String ekey = key.replace("Ext", "");
                                predicates.add(criteriaBuilder.equal(root.get(ekey), value));
                            } else {
                                predicates.add(criteriaBuilder.like(root.get(key).as(String.class), "%" + value + "%"));

                            }
                        }
                    }
                });
                if(filter.getSort().length > 0) {
                    List<Order> orders = new ArrayList<>();
                    Arrays.stream(filter.getSort()).forEach((x) -> {
                        Order order = null;
                        if (x.getField().equals("rateService")) {
                            order =  x.getType().equals("asc") ? criteriaBuilder.asc(root.get("servicePackage").get("rate")) : criteriaBuilder.desc(root.get("servicePackage").get("rate"));
                        } else {
                            order = x.getType().equals("asc") ? criteriaBuilder.asc(root.get(x.getField())) : criteriaBuilder.desc(root.get(x.getField()));
                        }
                        orders.add(order);
                    });
                        query.orderBy(orders);
                }
                    return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                }
        }, pageable);

        return pageRoomEntitys.map(roomEntity -> convert.convertToDto(roomEntity));
    }

    @Override
    public RoomDto findById(int id) {
        return convert.convertToDto(
                roomRepository.findById(id).
                        orElseThrow(() -> new NotFoundException("Room Not Found with id : " + id)));
    }

    @Override
    public RoomDto save(RoomDto roomDto) {
        Room room = new Room();
        Room roomByName = roomRepository.findByTitle(roomDto.getTitle()).orElse (null);
        if (roomDto.getId() != 0) {
            Room roomOld = roomRepository.findById(roomDto.getId()).orElseThrow(
                    () -> new NotFoundException("Room Not Found with id : " + roomDto.getId())

            );
            if(roomByName != null) {
                if(roomByName.getId() ==  roomOld.getId()){
                    room = roomRepository.save(convert.convertToEntity(roomDto, roomOld));
                }
                else {
                    throw new DuplicateRecordException( roomByName.getTitle() + " does exist in the database");
                }
            }
            else {
                room = roomRepository.save(convert.convertToEntity(roomDto, roomOld));
            }
        }
        else {
            if(roomByName == null){
                room = roomRepository.save(convert.convertToEntity(roomDto));
            } else {
                throw  new DuplicateRecordException( roomByName.getTitle() + " does exist in the database");
            }
        }
        return convert.convertToDto(room);
    }

    @Override
    public void delete(int id) {
        roomRepository.deleteById(id);
    }


    public RoomDto hiden(RoomDto roomDto) {
        Room room = new Room();
        Room roomOld = roomRepository.findById(roomDto.getId()).orElseThrow(
                () -> new NotFoundException("Room Not Found with id : " + roomDto.getId())
        );
        room = roomRepository.save(convert.convertToEntityHiden(roomDto, roomOld)
        );
        return convert.convertToDto(room);
    }
}
