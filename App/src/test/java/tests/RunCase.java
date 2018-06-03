package tests;

import org.junit.Assert;
import org.junit.Test;
import pages.Favorites;
import pages.firstTest;

public class RunCase extends firstTest {



    private String selectedProductName;

    private static final String testUserEmail = "EMAIL_";
    private static final String testUserPassword = "PASSWORD";
    private static final String searchTextToTest = "Samsung";
    private static final String pageNumberToNavigate = "3";

    @Test(priority = 1, groups = {"Home"})
    public void homePageTest_VerifyHomePage() {
        pages.HomePage homePage = new pages.HomePage(driver, wait);
        homePage.goToN11();
        homePage.isOnPage();
        Assert.assertEquals(true, homePage.isOnPage());
    }

    @Test(priority = 1, groups = {"Login"})
    public void loginTest_UserNamePassword() {
        pages.HomePage homePage = new pages.HomePage(driver, wait);
        homePage.goToLoginPage();

        pages.LoginPage loginPage = new pages.LoginPage(driver, wait);
        loginPage.loginToN11(testUserEmail, testUserPassword);
    }

    @Test(priority = 2, groups = {"Search"})
    public void searchProduct_VerifySearchFound() {
        pages.SearchPage searchPage = new pages.SearchPage(driver, wait);
        searchPage.searchProduct(searchTextToTest);
        Assert.assertFalse(searchPage.isAnyResultFound());
    }

    @Test(priority = 3, groups = {"Search"})
    public void navigateToPage_VerifyPageNumber() {
        pages.SearchPage searchPage = new pages.SearchPage(driver, wait);
        searchPage.navigateToPage(searchTextToTest, pageNumberToNavigate);
        Assert.assertEquals(searchPage.getPageNumber(), pageNumberToNavigate);
    }

    @Test(priority = 4, groups = {"Favorites"})
    public void addToFavorites_verifySelectedProduct() {
        pages.SearchPage searchPage = new pages.SearchPage(driver, wait);
        searchPage.selectThirdProduct();

        pages.ProductPage productPage = new pages.ProductPage(driver, wait);
        productPage.checkRequiredFields();

        String[] selectedProduct = productPage.addToFavorites();
        selectedProductName = selectedProduct[0];
        String selectedProductIdToCheck = selectedProduct[1];

        Favorites favoritesPage = new Favorites(driver, wait);
        Assert.assertEquals(favoritesPage.getActualProductName(selectedProductIdToCheck), selectedProductName);
    }

    @Test(priority = 4, groups = {"Favorites"}, dependsOnMethods = "addToFavorites_verifySelectedProduct")
    public void deleteFromFavorites_verifyDeletion() {
        Favorites favoritesPage = new Favorites(driver, wait);
        favoritesPage.deleteFromFavorites();

        Assert.assertFalse(favoritesPage.isFavoriteDeleted(selectedProductName));
    }
}