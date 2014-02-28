/* Data removing from Binary Search Tree. This method has one parameter get title
	of a book for removing. */
	public boolean remove(String title) 
	{
        Node parent = null;			// create parent reference to point parent node of a node	
		Node pointednode = root;	// create reference to point nodes	
		Node parentnode = root;		// create reference to get parent node temporarly
		char child = 'X';			// put status of child node as it is left or right. 'l' - Left and 'r' - Right
		boolean isRoot = true;		// to identify root node status. If some value passed the root node, isRoot will be false.
	    
		// Check node by title and go through the Binary Search Tree	until meets the node which should be delete
	    while (pointednode.book.title != title) 
		{	 
	        // If we should search to the left	
			isRoot = false;				// If it enters into find more, isRoot should be false. because counter passed the root
			parentnode = pointednode;
	        if (title.compareTo(pointednode.book.title) < 0) 	
			{	 
	            // Shift the pointed Node to the left child	node  
	            pointednode = pointednode.left;	
				child = 'l';	// set child status to 'l' - left
	        } 	
			else 	
			{	 
	            // Shift the pointed Node to the right child node 
	            pointednode = pointednode.right;
				child = 'r';	// set child status to 'r' - right
	        }		        
	    }
	    
		// If the node was not found, return false 
		if (pointednode == null) 
		{
            return false;
        }
		
		/* With this code,remove the node which should be deleted, if it is only a leaf node.
		The node set to null directly. */		
		if ((pointednode.left == null) && (pointednode.right == null)) // Check the pointed node has not any child nodes
		{
			if(isRoot == true) // If isRoot is true, it is root. Then it will be set to null and remove
			{
				root = null;
			}
			else
			{
				/* Check if the node which should be deleted is from left or right node from its parent node.
				We set it to null from it's parent node. This method is used to delete unreferenced objects
				from the scope */
				if(child == 'l')		
				parentnode.left = null;		// If child is left, set left child of parent node to left
				else
				parentnode.right = null;	// If child is right, set right child of parent node to left
				System.gc();				// Activate garbage collection to delete null referenced objects
			}			
			return true; // Return to the caller with successfull true value
        }
		
		/* With this code,remove the node which should be deleted, if it is with both left and right children.
		The node set to null with procedure. */
		if ((pointednode.left != null) && (pointednode.right != null)) // Check the pointed node has both child nodes
		{            
			if(isRoot == true)	// If isRoot is true, it is root. Then ,
			{				
				//Here I used a special method to get node with minimum value and to delete current node
				root.book = getMinimum(root.right).book; 	// remove root node with putting appropriate node in that place
			}
			else
			{
				/* Check if the node which should be deleted is from left or right node from its parent node. 
				Again I use getMinimum value(Node) method to find node with minimum value and to return it with 
				deleting current node. Here the node will not be deleted physically. It will restore with returning book values*/
				if(child == 'l')
				parentnode.left.book = getMinimum(pointednode.right).book; 
				else
				parentnode.right.book = getMinimum(pointednode.right).book;
				System.gc();	// Activate garbage collection to delete null referenced objects				
			}			
            return true;		// Return to the caller with successfull true value
        }
		
		/* With this code,remove the node which should be deleted, if it is with left child.
		The node set to null with combining left child of pointed node. */
        if (pointednode.left != null) 
		{
			if(isRoot == true)	// If isRoot is true, it is root. Then it will be set to null and remove
			{
				root = pointednode.left;
			}
			else
			{
				/* Check if the node which should be deleted is from left or right node from its parent node. 
				Node will restore with next left child node book values*/
				if(child == 'l')
				parentnode.left = pointednode.left;
				else
				parentnode.right = pointednode.left;
				System.gc();	// Activate garbage collection to delete null referenced objects
				pointednode.left = null;	// Left child node of pointed node set to null after get its values. 
			}
            return true;	// Return to the caller with successfull true value
        }

		/* With this code,remove the node which should be deleted, if it is with right child.
		The node set to null with combining right child of pointed node. */
        if (pointednode.right != null) 
		{
			if(isRoot == true)	// If isRoot is true, it is root. Then it will be set to null and remove
			{
				root = pointednode.right;
			}
			else
			{
				/* Check if the node which should be deleted is from left or right node from its parent node. 
				Node will restore with next right child node book values*/
				if(child == 'l')
				parentnode.left = pointednode.right;
				else
				parentnode.right = pointednode.right;
				System.gc();	// Activate garbage collection to delete null referenced objects
				pointednode.right = null;	// Right child node of pointed node set to null after get its values.
			}		
            return true;	// Return to the caller with successfull true value
        }
		
		parent = pointednode;        
		return remove(title);
    }