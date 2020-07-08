package br.com.project.storage.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.project.storage.domain.model.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long> {

}
