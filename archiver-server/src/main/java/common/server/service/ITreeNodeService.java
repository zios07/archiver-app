package common.server.service;

import common.server.domain.TreeNode;
import common.server.exception.NotFoundException;

import java.util.List;

public interface ITreeNodeService {

	TreeNode addTreeNode(TreeNode treeNode);

	TreeNode findTreeNode(long id) throws NotFoundException;

	List<TreeNode> findAllTreeNodes();

	void deleteTreeNode(Long id) throws NotFoundException;

	List<TreeNode> searchTreeNodes(TreeNode treeNode) throws NotFoundException;
	
	TreeNode findTreeNodeByUsername(String username) throws NotFoundException;

    List<TreeNode> getConnectedUserTreeNodes();

    List<TreeNode> addMultipleNodes(TreeNode[] nodes);

	List<TreeNode> getUserTree();

	TreeNode updateNode(TreeNode node);
}
