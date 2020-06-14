package com.urazem.elifbe.repository;

import com.urazem.elifbe.model.Material;
import com.urazem.elifbe.model.SetWords;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Integer> {
    List<Material> findById(int id);

    List<Material> findByName(String name);
}

