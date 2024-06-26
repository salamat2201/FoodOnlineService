package com.example.aiymct_dp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class HelloController {
    @FXML
    private Text contactempty;

    @FXML
    private TextArea contactmain;

    @FXML
    private TextField contactname;

    @FXML
    private Pane contactpane;
    @FXML
    public ImageView bookImg;

    @FXML
    public ImageView choose1;

    @FXML
    public ImageView choose2;
    @FXML
    public ImageView choose3;
    @FXML
    public Text aboutbelkafood;
    @FXML
    public VBox paneForObserver;
    @FXML
    public Text abouttime;
    @FXML
    private TextField clientName;
    @FXML
    public Text descriptionFirst;
    @FXML
    public Text descriptionSecond;
    @FXML
    public Text descriptionThird;
    @FXML
    private Text subTotalPrice;
    @FXML
    public Text paymentMethod;
    @FXML
    public Pane generalPane;
    @FXML
    public Pane orderDetailsPane;
    @FXML
    public ImageView orderdetailsphoto;
    @FXML
    public Pane pane1;
    @FXML
    private Text totalPrice;
    @FXML
    public Pane pane11;
    @FXML
    public Pane pane111;
    @FXML
    private Text discountPrice;
    @FXML
    public Text priceFirstProduct;
    @FXML
    public Text priceSecondProduct;
    @FXML
    public Text priceThirdProduct;
    @FXML
    public Text titleFirstProduct;
    @FXML
    public Text titleSecondProduct;
    @FXML
    public Text titleThirdProduct;
    @FXML
    public TextField search;
    @FXML
    private VBox korzina;
    @FXML
    private Pane CenterPane;
    @FXML
    private Pane paymentPane;
    @FXML
    private Pane cardListPane;
    @FXML
    private Pane bookPane;
    @FXML
    public ImageView halyk;
    @FXML
    public ImageView kaspi;
    @FXML
    public Pane paymentBackPane;
    static HashMap<AtomicInteger, Integer> map = new LinkedHashMap<>();
    static int t = 0;
    DiscountStrategy discountStrategy = null;
    @FXML
    public VBox inOrderPane;
    @FXML
    public VBox inProcessPane;
    @FXML
    public VBox donePane;
    @FXML
    private Button bookbutton;
    @FXML
    private Text emptybook;
    @FXML
    private TextField mailbook;

    @FXML
    private TextField namebook;
    @FXML
    private TextField timebook;
    @FXML
    private TextField datebook;
    private final Command halykBankCommand = new HalykBankCommand();
    private final Command kaspiBankCommand = new KaspiBankCommand();

    public HelloController() {
    }


    @FXML
    void booked() {
        this.CenterPane.setVisible(false);
        this.cardListPane.setVisible(false);
        this.search.setVisible(false);
        this.bookPane.setVisible(true);
        this.contactpane.setVisible(false);
    }

    @FXML
    void namechange() {
        this.emptybook.setText("");
    }
    @FXML
    void contactnamechange() {
        this.contactempty.setText("");
    }
    @FXML
    void contactbutton() {
        this.contactempty.setText("Thanks for the feedback " + this.contactname.getText());
        this.contactname.setText("");
        this.contactmain.setText("");
    }

    @FXML
    void contactImg() {
        this.CenterPane.setVisible(false);
        this.cardListPane.setVisible(false);
        this.search.setVisible(false);
        this.bookPane.setVisible(false);
        this.contactpane.setVisible(true);
    }

    @FXML
    void bookedplace(ActionEvent event) throws FileNotFoundException {
        if(!mailbook.getText().contains("@")) {
            ErrorPassOrName("must be @");
            return;
        }
        if (namebook.getText().isEmpty() || mailbook.getText().isEmpty() || datebook.getText().isEmpty() || timebook.getText().isEmpty()) {
            ErrorPassOrName("The fields are empty");
            return;
        }
        this.emptybook.setText("“Booking has been completed”");
        this.mailbook.setText("");
        this.namebook.setText("");
        this.datebook.setText("");
        this.timebook.setText("");
    }
    @FXML
    void addFirstProduct() {
        this.paneForObserver.setVisible(false);
        this.korzina.setVisible(true);
        AtomicInteger san = new AtomicInteger(1);
        this.orderDetailsPane.setVisible(false);
        this.paymentPane.setVisible(true);
        Text text = new Text();
        text.setFont(Font.font("tahoma", FontWeight.BOLD, 15));
        if (this.titleFirstProduct.getText().equals("Combo 1")) {
            text.setText(this.titleFirstProduct.getText());
        } else {
            text.setText(this.descriptionFirst.getText());

        }

        AtomicInteger priceProduct = new AtomicInteger();
        Button min = new Button("-");
        Text cnt = new Text(String.valueOf(san.get()));
        Button plus = new Button("+");
        min.setStyle("-fx-background-color: white; -fx-text-fill: #000");
        plus.setStyle("-fx-background-color: white");
        StringBuilder a = new StringBuilder();

        for(int i = 0; i < this.priceFirstProduct.getText().length(); ++i) {
            if (Character.isDigit(this.priceFirstProduct.getText().charAt(i))) {
                a.append(this.priceFirstProduct.getText().charAt(i));
            }
        }

        priceProduct.set(Integer.parseInt(a.toString()));
        map.put(priceProduct, 1);
        HBox hBox = new HBox();
        hBox.getChildren().addAll(text, min, cnt, plus);
        hBox.setPadding(new Insets(10.0));
        hBox.setSpacing(10.0);
        this.korzina.getChildren().add(hBox);
        plus.setOnAction((event1) -> {
            san.addAndGet(1);
            cnt.setText(String.valueOf(san.get()));
            map.put(priceProduct, map.getOrDefault(priceProduct, 0) + 1);
        });
        min.setOnAction((event1) -> {
            san.addAndGet(-1);
            if (san.get() == 0) {
                this.korzina.getChildren().remove(hBox);
                map.remove(priceProduct);
            } else {
                cnt.setText(String.valueOf(san.get()));
                map.put(priceProduct, map.getOrDefault(priceProduct, 0) - 1);
            }

        });
    }

    @FXML
    void addSecondProduct() {
        this.paneForObserver.setVisible(false);
        this.korzina.setVisible(true);
        AtomicInteger san = new AtomicInteger(1);
        this.orderDetailsPane.setVisible(false);
        this.paymentPane.setVisible(true);
        Text text = new Text();
        text.setFont(Font.font("tahoma", FontWeight.BOLD, 15));
        if (this.titleSecondProduct.getText().equals("Combo 2")) {
            text.setText(this.titleSecondProduct.getText());
        } else {
            text.setText(this.descriptionSecond.getText());
        }

        AtomicInteger priceProduct = new AtomicInteger();
        Button min = new Button("-");
        Text cnt = new Text(String.valueOf(san.get()));
        Button plus = new Button("+");
        min.setStyle("-fx-background-color: white; -fx-text-fill: #000");
        plus.setStyle("-fx-background-color: white");
        StringBuilder a = new StringBuilder();

        for(int i = 0; i < this.priceSecondProduct.getText().length(); ++i) {
            if (Character.isDigit(this.priceSecondProduct.getText().charAt(i))) {
                a.append(this.priceSecondProduct.getText().charAt(i));
            }
        }

        priceProduct.set(Integer.parseInt(a.toString()));
        map.put(priceProduct, 1);
        HBox hBox = new HBox();
        hBox.getChildren().addAll(text, min, cnt, plus);
        hBox.setPadding(new Insets(10.0));
        hBox.setSpacing(10.0);
        this.korzina.getChildren().add(hBox);
        plus.setOnAction((event1) -> {
            san.addAndGet(1);
            cnt.setText(String.valueOf(san.get()));
            map.put(priceProduct, map.getOrDefault(priceProduct, 0) + 1);
        });
        min.setOnAction((event1) -> {
            san.addAndGet(-1);
            if (san.get() == 0) {
                this.korzina.getChildren().remove(hBox);
                map.remove(priceProduct);
            } else {
                cnt.setText(String.valueOf(san.get()));
                map.put(priceProduct, map.getOrDefault(priceProduct, 0) - 1);
            }

        });
    }

    @FXML
    void addThirdProduct() {
        this.paneForObserver.setVisible(false);
        this.korzina.setVisible(true);
        AtomicInteger san = new AtomicInteger(1);
        this.orderDetailsPane.setVisible(false);
        this.paymentPane.setVisible(true);
        Text text = new Text();
        text.setFont(Font.font("tahoma", FontWeight.BOLD, 15));
        if (this.titleThirdProduct.getText().equals("Combo 3")) {
            text.setText(this.titleThirdProduct.getText());
        } else {
            text.setText(this.descriptionThird.getText());
        }

        AtomicInteger priceProduct = new AtomicInteger();
        Button min = new Button("-");
        Text cnt = new Text(String.valueOf(san.get()));
        cnt.setFont(Font.font("tahoma", 15));
        Button plus = new Button("+");
        min.setStyle("-fx-background-color: white; -fx-text-fill: #000");
        plus.setStyle("-fx-background-color: white");
        StringBuilder a = new StringBuilder();

        for(int i = 0; i < this.priceThirdProduct.getText().length(); ++i) {
            if (Character.isDigit(this.priceThirdProduct.getText().charAt(i))) {
                a.append(this.priceThirdProduct.getText().charAt(i));
            }
        }

        priceProduct.set(Integer.parseInt(a.toString()));
        map.put(priceProduct, 1);
        HBox hBox = new HBox();
        hBox.getChildren().addAll(text, min, cnt, plus);
        hBox.setPadding(new Insets(10.0));
        hBox.setSpacing(10.0);
        this.korzina.getChildren().add(hBox);
        plus.setOnAction((event1) -> {
            san.addAndGet(1);
            cnt.setText(String.valueOf(san.get()));
            map.put(priceProduct, map.getOrDefault(priceProduct, 0) + 1);
        });
        min.setOnAction((event1) -> {
            san.addAndGet(-1);
            if (san.get() == 0) {
                this.korzina.getChildren().remove(hBox);
                map.remove(priceProduct);
            } else {
                cnt.setText(String.valueOf(san.get()));
                map.put(priceProduct, map.getOrDefault(priceProduct, 0) - 1);
            }

        });
    }

    @FXML
    void korzinaDone() {
        int sum = 0;

        Map.Entry entry;
        for(Iterator<Map.Entry<AtomicInteger, Integer>> var3 = map.entrySet().iterator(); var3.hasNext(); sum += ((AtomicInteger)entry.getKey()).get() * (Integer)entry.getValue()) {
            entry = var3.next();
        }

        if (clientName.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please, enter your name");
            alert.showAndWait();
            return;
        }

        this.subTotalPrice.setText("Sub Total Price: " + sum + "KZT");
        this.checkSum(sum);
        double discount = this.discountStrategy.calculatePrice(sum);
        this.discountPrice.setText("Discount : " + discount + "KZT");
        this.totalPrice.setText("Total : " + (sum - (int)discount) + "KZT");
        t = sum - (int)discount;
        map.clear();
    }

    void checkSum(int sum) {
        if (sum >= 5000 && sum < 7000) {
            this.discountStrategy = new FiveToSevenStrategy();
        } else if (sum >= 7000 && sum < 10000) {
            this.discountStrategy = new SevenToTenStrategy();
        } else if (sum >= 10000) {
            this.discountStrategy = new MoreThanTenStrategy();
        } else {
            this.discountStrategy = new NullStrategy();
        }

    }

    @FXML
    void clickCardList() {
        this.CenterPane.setVisible(false);
        this.search.setVisible(true);
        this.bookPane.setVisible(false);
        this.cardListPane.setVisible(true);
        this.contactpane.setVisible(false);
    }

    @FXML
    void clickHome() {
        this.cardListPane.setVisible(false);
        this.bookPane.setVisible(false);
        this.search.setVisible(true);
        this.CenterPane.setVisible(true);
        this.contactpane.setVisible(false);
    }

    @FXML
    void clickBurger() {
        ProductFactory burgerFactory = new BurgerFactory();
        Product burger = burgerFactory.createProduct();
        burger.createProduct(this);
        Image chicken = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/chickenn.png")));
        choose1.setImage(chicken);
        Image beef = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/cow.png")));
        choose2.setImage(beef);
        Image asorti = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/asorti.png")));
        choose3.setImage(asorti);
    }

    @FXML
    void clickPizza() {
        ProductFactory pizzaFactory = new PizzaFactory();
        Product pizza = pizzaFactory.createProduct();
        pizza.createProduct(this);
        Image pizza1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/pizza1.png")));
        choose1.setImage(pizza1);
        Image pizza2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/pizza2.png")));
        choose2.setImage(pizza2);
        Image pizza3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/pizza3.png")));
        choose3.setImage(pizza3);
    }


    @FXML
    void clickDoner() {
        ProductFactory donerFactory = new DonerFactory();
        Product doner = donerFactory.createProduct();
        doner.createProduct(this);
        Image chicken = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/chickenn.png")));
        choose1.setImage(chicken);
        Image beef = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/cow.png")));
        choose2.setImage(beef);
        Image asorti = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/asorti.png")));
        choose3.setImage(asorti);
    }

    @FXML
    void clickDrink() {
        ProductFactory drinkFactory = new DrinkFactory();
        Product drink = drinkFactory.createProduct();
        drink.createProduct(this);
        Image cola = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/cola.png")));
        choose1.setImage(cola);
        Image fanta = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/fanta.png")));
        choose2.setImage(fanta);
        Image sprite = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/sprite.png")));
        choose3.setImage(sprite);
    }

    @FXML
    void clickPide() {
        ProductFactory pideFactory = new PideFactory();
        Product pide = pideFactory.createProduct();
        pide.createProduct(this);
        Image cheese = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/cheese.png")));
        choose1.setImage(cheese);
        Image egg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/egg.png")));
        choose2.setImage(egg);
        Image sausage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/sausage.png")));
        choose3.setImage(sausage);
    }

    @FXML
    void clickChicken() {
        ProductFactory comboFactory = new ChickenFactory();
        Product combo = comboFactory.createProduct();
        combo.createProduct(this);
        Image combo1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/combo1.png")));
        choose1.setImage(combo1);
        Image combo2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/combo2.png")));
        choose2.setImage(combo2);
        Image combo3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/combo3.png")));
        choose3.setImage(combo3);
    }

    @FXML
    void halykBankClick() {
        PaymentProcessor cardPaymentProcessor = new HalykPaymentAdapter(new HalykBankPaymentSystem());
        cardPaymentProcessor.processPayment(this, t);
        this.korzina.setVisible(false);
        this.orderDetailsPane.setVisible(true);
        this.paymentBackPane.setVisible(true);
        this.korzina.getChildren().clear();
        Text text = new Text(this.clientName.getText());
        this.clientName.setText("");
        this.inOrderPane.getChildren().add(text);
        ProgressBar progressBar = new ProgressBar();
        text.setFill(Paint.valueOf("black"));
        progressBar.setMinHeight(25.0);
        progressBar.setMinWidth(120.0);
        progressBar.setVisible(false);
        progressBar.setProgress(0.0);
        text.setFont(Font.font("tahoma",15));
        text.setFill(Paint.valueOf("white"));
        StackPane stackPane = new StackPane();
        this.halykBankCommand.execute(this, text, progressBar, stackPane);
    }

    @FXML
    void kaspiBankClick() {
        PaymentProcessor cardPaymentProcessor = new KaspiPaymentAdapter(new KaspiBankPaymentSystem());
        cardPaymentProcessor.processPayment(this, t);
        this.korzina.setVisible(false);
        this.orderDetailsPane.setVisible(true);
        this.paymentBackPane.setVisible(true);
        this.korzina.getChildren().clear();
        Text text = new Text(this.clientName.getText());
        this.clientName.setText("");
        this.inOrderPane.getChildren().add(text);
        text.setFont(Font.font("tahoma",15));
        text.setFill(Paint.valueOf("white"));
        ProgressBar progressBar = new ProgressBar();
        text.setFill(Paint.valueOf("black"));
        progressBar.setMinHeight(25.0);
        progressBar.setMinWidth(120.0);
        progressBar.setVisible(false);
        progressBar.setProgress(0.0);
        StackPane stackPane = new StackPane();
        this.kaspiBankCommand.execute(this, text, progressBar, stackPane);
    }

    @FXML
    void backPayment() {
        this.paymentPane.setVisible(false);
        this.paymentBackPane.setVisible(false);
        this.korzina.getChildren().removeAll();
        this.subTotalPrice.setText("Sub Total Price : ");
        this.discountPrice.setText("Discount : ");
        this.totalPrice.setText("Total : ");
        this.paymentMethod.setText("Payment Method : ");
        this.kaspi.setVisible(true);
        this.halyk.setVisible(true);
    }

    @FXML
    void search() {
        if (this.search.getText().equalsIgnoreCase("burger")) {
            this.clickBurger();
        } else if (this.search.getText().equalsIgnoreCase("pizza")) {
            this.clickPizza();
        } else if (this.search.getText().equalsIgnoreCase("Pide")) {
            this.clickPide();
        } else if (this.search.getText().equalsIgnoreCase("doner")) {
            this.clickDoner();
        } else if (this.search.getText().equalsIgnoreCase("pizza")) {
            this.clickPizza();
        } else if (this.search.getText().equalsIgnoreCase("drink")) {
            this.clickDrink();
        } else if (this.search.getText().equalsIgnoreCase("combo")) {
            this.clickChicken();
        } else if (this.search.getText().contains("Beef") || this.search.getText().contains("Chicken") || this.search.getText().contains("Assorti")) {
            this.clickBurger();
        } else if (this.search.getText().contains("Margarita") || this.search.getText().contains("Pepperoni") || this.search.getText().contains("Chetyre Sezona")) {
            this.clickPizza();
        } else if (this.search.getText().contains("Beef Doner") || this.search.getText().contains("Chicken Doner") || this.search.getText().contains("Assorti Doner")) {
            this.clickDoner();
        } else if (this.search.getText().contains("Coca Cola") || this.search.getText().contains("Fanta") || this.search.getText().contains("Sprite")) {
            this.clickDrink();
        } else if (this.search.getText().contains("cheese") || this.search.getText().contains("eggs") || this.search.getText().contains("sausage")) {
            this.clickPide();
        }
    }
    void ErrorPassOrName(String mess) throws FileNotFoundException {
        Stage stage = new Stage();
        Button ok = new Button("OK");
        Button cancel = new Button("Cancel");
        Text text = new Text(mess + ", try again!");
        text.setFill(Color.WHITE);
        HBox hBox = new HBox(8, text);
        hBox.setAlignment(Pos.CENTER);
        HBox hBox1 = new HBox(8, ok, cancel);
        hBox1.setAlignment(Pos.CENTER);
        VBox vBox = new VBox(25, hBox, hBox1);
        vBox.setAlignment(Pos.CENTER);
        StackPane stackPane = new StackPane(vBox);
        stackPane.setAlignment(Pos.CENTER);
        Scene scene = new Scene(stackPane, 450, 200);
        text.setStyle("-fx-font-weight: 500;-fx-font-size: 14");
        ok.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-border-radius: 7px;-fx-background-radius: 7px; -fx-border-color: black");
        cancel.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-border-radius: 7px;-fx-background-radius: 7px;-fx-border-color: black");
        stackPane.setStyle("-fx-background-color: #061251");
        stage.setScene(scene);
        stage.show();
        ok.setOnAction(e -> {
            Stage stage1 = (Stage) ok.getScene().getWindow();
            stage1.close();
            this.mailbook.setText("");
            this.namebook.setText("");
            this.datebook.setText("");
            this.timebook.setText("");
        });
        cancel.setOnAction(e -> {
            Stage stage1 = (Stage) cancel.getScene().getWindow();
            stage1.close();
        });
    }
}
