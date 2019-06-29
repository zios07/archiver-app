package common.server.repository;

import common.server.domain.TreeNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface TreeNodeRepository extends JpaRepository<TreeNode, Long> {

    List<TreeNode> findByUsername(String username);

    @Modifying
    void deleteByParent(String idInTree);
}
