package com.canteen.controller;

import com.canteen.model.service.MenuService;
import com.canteen.model.service.OrderService;
import com.canteen.model.service.AdminService;
import com.canteen.view.AdminView;
import com.canteen.view.AdminLoginDialog;
import com.canteen.model.MenuItem;
import com.canteen.model.Order;

import javax.swing.*;
import java.util.List;

/**
 * Controller for Admin operations
 * Handles admin functionality and coordinates with AdminView
 */
public class AdminController {
    private MenuService menuService;
    private OrderService orderService;
    private AdminService adminService;
    private AdminView view;
    
    public AdminController(MenuService menuService, OrderService orderService, AdminService adminService) {
        this.menuService = menuService;
        this.orderService = orderService;
        this.adminService = adminService;
        this.view = new AdminView(this);
    }
    
    public AdminView getView() {
        return view;
    }
    
    public void showLoginDialog(JFrame parent, Runnable onSuccess) {
        AdminLoginDialog loginDialog = new AdminLoginDialog(parent, this);
        loginDialog.setVisible(true);
        
        if (loginDialog.isLoginSuccessful()) {
            onSuccess.run();
        }
    }
    
    public boolean authenticateAdmin(String username, String password) {
        return adminService.authenticate(username, password);
    }
    
    public void refreshData() {
        view.refreshMenuItems();
        view.refreshOrders();
    }
    
    // Menu Management Methods
    public List<MenuItem> getAllMenuItems() {
        return menuService.getAllMenuItems();
    }
    
    public MenuItem getMenuItemById(int id) {
        return menuService.getMenuItemById(id);
    }
    
    public boolean addMenuItem(MenuItem item) {
        try {
            boolean success = menuService.addMenuItem(item);
            if (success) {
                view.showMessage("Menu item added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                view.refreshMenuItems();
                return true;
            } else {
                view.showMessage("Failed to add menu item.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception e) {
            view.showMessage("Error adding menu item: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public boolean updateMenuItem(MenuItem item) {
        try {
            boolean success = menuService.updateMenuItem(item);
            if (success) {
                view.showMessage("Menu item updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                view.refreshMenuItems();
                return true;
            } else {
                view.showMessage("Failed to update menu item.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception e) {
            view.showMessage("Error updating menu item: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public boolean deleteMenuItem(int id) {
        try {
            int confirm = JOptionPane.showConfirmDialog(
                view, 
                "Are you sure you want to delete this menu item?", 
                "Confirm Delete", 
                JOptionPane.YES_NO_OPTION
            );
            
            if (confirm == JOptionPane.YES_OPTION) {
                boolean success = menuService.deleteMenuItem(id);
                if (success) {
                    view.showMessage("Menu item deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    view.refreshMenuItems();
                    return true;
                } else {
                    view.showMessage("Failed to delete menu item.", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            return false;
        } catch (Exception e) {
            view.showMessage("Error deleting menu item: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    // Order Management Methods
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
    
    public Order getOrderById(int id) {
        return orderService.getOrderById(id);
    }
    
    public boolean updateOrderStatus(int orderId, String status) {
        try {
            boolean success = orderService.updateOrderStatus(orderId, status);
            if (success) {
                view.showMessage("Order status updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                view.refreshOrders();
                return true;
            } else {
                view.showMessage("Failed to update order status.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception e) {
            view.showMessage("Error updating order status: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    // Validation Methods
    public boolean validateMenuItem(String name, String priceText, String category) {
        if (name == null || name.trim().isEmpty()) {
            view.showMessage("Please enter item name.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (priceText == null || priceText.trim().isEmpty()) {
            view.showMessage("Please enter item price.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        try {
            double price = Double.parseDouble(priceText.trim());
            if (price < 0) {
                view.showMessage("Price must be a positive number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            view.showMessage("Please enter a valid price.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (category == null || category.trim().isEmpty()) {
            view.showMessage("Please enter item category.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
}
