package com.exercise.FlowDeliverDemo.dao;

import com.exercise.FlowDeliverDemo.entity.CandyData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
@Repository
@Transactional
public class CandyDaoImpl implements CandyDAO {
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    private static final String FIND_ALL_CANDY = "select * from candy";
    private static final String GET_CANDY_BY_DELIVERY_ID = "select * from candy c " +
            "join candy_delivery cd on c.id = cd.candy_id where cd.delivery_id =: deliveryId";

    private static final String INSERT_DELIVERY = "insert into candy_delivery(candy_id, delivery_id)" +
            " values (:candyId, :deliveryId)";


    private static final RowMapper<CandyData> candyDataRowMapper= new BeanPropertyRowMapper<>(CandyData.class);

    @Override
    public List<CandyData> findAll() {
        return jdbcTemplate.query(FIND_ALL_CANDY,candyDataRowMapper);
    }

    @Override
    public CandyData create(String name, BigDecimal price) {
        return null;
    }

    @Override
    public List<CandyData> getCandyByDeliveryId(Long deliveryId) {
        return jdbcTemplate.query(
                GET_CANDY_BY_DELIVERY_ID,
                new MapSqlParameterSource().addValue("deliveryId", deliveryId),
                candyDataRowMapper
        );
    }

    @Override
    public void addCandyToDelivery(Long candyId, Long deliveryId) {
        jdbcTemplate.update(INSERT_DELIVERY,
                new MapSqlParameterSource().addValue("candyId",candyId).addValue("deliveryId", deliveryId)
        );
    }
}
