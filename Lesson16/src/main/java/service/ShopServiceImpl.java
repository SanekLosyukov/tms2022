package service;

import exception.ProductAlreadyExistException;
import model.Product;
import model.Shop;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ShopServiceImpl implements ShopService {
    private final Shop shop;

    public ShopServiceImpl(Shop shop) {
        this.shop = shop;
    }

    @Override
    public List<Product> getAllProducts() {
        return shop.getProducts();
    }

    @Override
    public Product getProductById(int id) {
        return findById(id)
                .orElseThrow(() -> new NoSuchElementException("Товар с ID = " + id + " не найден."));
    }

    @Override
    public void takeProduct(Product product) throws ProductAlreadyExistException {
        if (!isProductExist(product)) {
            shop.getProducts().add(product);
        } else {
            throw new ProductAlreadyExistException("Товар с ID = " + product.getId() + " уже есть в магазине");
        }
    }

    @Override
    public void editProduct(int id, Product product) throws ProductAlreadyExistException {
        if (isProductExist(product)) {
            Product sourceProduct = getProductById(id);
            sourceProduct.setName(product.getName());
            sourceProduct.setPrice(product.getPrice());
        } else {
            throw new ProductAlreadyExistException("Товара с ID = " + product.getId() + " нет в магазине");
        }
    }

    @Override
    public void removeProduct(int id) {
        Product product = getProductById(id);
        shop.getProducts().remove(product);
    }

    @Override
    public boolean isWorking() {
        return shop.isWorking();
    }

    @Override
    public void closeShop() {
        shop.setWorking(false);
    }

    private boolean isProductExist(Product product) {
        return shop.getProducts().stream()
                .anyMatch(p -> p.getId() == product.getId());
    }

    private Optional<Product> findById(int id) {
        return shop.getProducts().stream()
                .filter(g -> g.getId() == id)
                .findFirst();
    }
}
