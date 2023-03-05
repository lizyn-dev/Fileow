package org.zlycerqan.fileow.item;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, String> {

    List<Item> findByChannelOrderByCreateTimeDesc(String channel);

}