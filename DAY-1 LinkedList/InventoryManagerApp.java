class Item {
    String name;
    int id;
    int quantity;
    double price;
    Item next;
    public Item(String name, int id, int quantity, double price) {
        this.name = name;
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.next = null;
    }
}
class Inventory {
    private Item head;
    public void addAtBeginning(String name, int id, int quantity, double price) {
        Item newItem = new Item(name, id, quantity, price);
        newItem.next = head;
        head = newItem;
    }
    public void addAtEnd(String name, int id, int quantity, double price) {
        Item newItem = new Item(name, id, quantity, price);
        if (head == null) {
            head = newItem;
            return;
        }
        Item temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newItem;
    }
    public void addAtPosition(int position, String name, int id, int quantity, double price) {
        if (position <= 1 || head == null) {
            addAtBeginning(name, id, quantity, price);
            return;
        }
        Item newItem = new Item(name, id, quantity, price);
        Item temp = head;
        int index = 1;
        while (index < position - 1 && temp.next != null) {
            temp = temp.next;
            index++;
        }
        newItem.next = temp.next;
        temp.next = newItem;
    }
    public void removeById(int id) {
        if (head == null) return;
        if (head.id == id) {
            head = head.next;
            System.out.println("Item with ID " + id + " removed.");
            return;
        }
        Item temp = head;
        while (temp.next != null && temp.next.id != id) {
            temp = temp.next;
        }
        if (temp.next == null) {
            System.out.println("Item with ID " + id + " not found.");
            return;
        }
        temp.next = temp.next.next;
        System.out.println("Item with ID " + id + " removed.");
    }
    public void updateQuantity(int id, int newQty) {
        Item temp = head;
        while (temp != null) {
            if (temp.id == id) {
                temp.quantity = newQty;
                System.out.println("Quantity updated for ID " + id);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item with ID " + id + " not found.");
    }
    public void searchById(int id) {
        Item temp = head;
        while (temp != null) {
            if (temp.id == id) {
                printItem(temp);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item with ID " + id + " not found.");
    }
    public void searchByName(String name) {
        Item temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.name.equalsIgnoreCase(name)) {
                printItem(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No item with name '" + name + "' found.");
    }
    public void displayAll() {
        if (head == null) {
            System.out.println("Inventory is empty.");
            return;
        }
        System.out.println("\nInventory Items:");
        Item temp = head;
        while (temp != null) {
            printItem(temp);
            temp = temp.next;
        }
    }
    public void calculateTotalValue() {
        double total = 0;
        Item temp = head;
        while (temp != null) {
            total += temp.price * temp.quantity;
            temp = temp.next;
        }
        System.out.println("\nTotal Inventory Value: $" + total);
    }
    private void printItem(Item item) {
        System.out.println("ID: " + item.id + ", Name: " + item.name + ", Qty: " + item.quantity + ", Price: $" + item.price);
    }

    public void sortByName(boolean ascending) {
        head = mergeSortByName(head, ascending);
        System.out.println("\nInventory sorted by Name (" + (ascending ? "ASC" : "DESC") + ")");
    }

    public void sortByPrice(boolean ascending) {
        head = mergeSortByPrice(head, ascending);
        System.out.println("\nInventory sorted by Price (" + (ascending ? "ASC" : "DESC") + ")");
    }

    private Item mergeSortByName(Item node, boolean asc) {
        if (node == null || node.next == null) return node;
        Item middle = getMiddle(node);
        Item nextToMiddle = middle.next;
        middle.next = null;

        Item left = mergeSortByName(node, asc);
        Item right = mergeSortByName(nextToMiddle, asc);

        return sortedMergeByName(left, right, asc);
    }

    private Item mergeSortByPrice(Item node, boolean asc) {
        if (node == null || node.next == null) return node;
        Item middle = getMiddle(node);
        Item nextToMiddle = middle.next;
        middle.next = null;

        Item left = mergeSortByPrice(node, asc);
        Item right = mergeSortByPrice(nextToMiddle, asc);

        return sortedMergeByPrice(left, right, asc);
    }

    private Item sortedMergeByName(Item a, Item b, boolean asc) {
        if (a == null) return b;
        if (b == null) return a;

        Item result;
        if ((asc && a.name.compareToIgnoreCase(b.name) <= 0) ||
                (!asc && a.name.compareToIgnoreCase(b.name) > 0)) {
            result = a;
            result.next = sortedMergeByName(a.next, b, asc);
        } else {
            result = b;
            result.next = sortedMergeByName(a, b.next, asc);
        }
        return result;
    }

    private Item sortedMergeByPrice(Item a, Item b, boolean asc) {
        if (a == null) return b;
        if (b == null) return a;

        Item result;
        if ((asc && a.price <= b.price) || (!asc && a.price > b.price)) {
            result = a;
            result.next = sortedMergeByPrice(a.next, b, asc);
        } else {
            result = b;
            result.next = sortedMergeByPrice(a, b.next, asc);
        }
        return result;
    }

    private Item getMiddle(Item node) {
        if (node == null) return null;
        Item slow = node, fast = node.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}

public class InventoryManagerApp {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();

        inventory.addAtEnd("Mouse", 101, 50, 15.5);
        inventory.addAtBeginning("Keyboard", 102, 30, 25.0);
        inventory.addAtEnd("Monitor", 103, 20, 150.0);
        inventory.addAtPosition(2, "Laptop", 104, 10, 999.99);

        inventory.displayAll();

        inventory.removeById(103);
        inventory.displayAll();

        inventory.updateQuantity(104, 12);

        inventory.searchById(102);
        inventory.searchByName("Laptop");

        inventory.calculateTotalValue();

        inventory.sortByName(true);
        inventory.displayAll();

        inventory.sortByPrice(false);
        inventory.displayAll();
    }
}
