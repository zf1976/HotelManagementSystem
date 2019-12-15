package mvcpro.controlled;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import mvcpro.model.entity.User;
import mvcpro.model.dao.UserDao;
import mvcpro.model.utils.MD5;
import mvcpro.view.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class UiLoginController {

    private  static boolean isTure=false;

    private UserDao userDao;

    private Stage loginStage;

    private Stage signInStage;

    private UiMainFrame uiMainFrame;

    private UiMessageBox uiMessageBox;

    @FXML
    private Button btnSignIn;

    @FXML
    private TextField loginID;

    @FXML
    private ImageView image;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private Button loginCheck;

    @FXML
    private Button loginExit;

    @FXML
    private ChoiceBox selectUserType;

    private StringBuilder verify;


    @FXML
    void initialize() throws Exception {
        uiMainFrame=new UiMainFrame();
        uiMainFrame.start(new Stage());

        signInStage=new Stage();
        uiMessageBox=new UiMessageBox();
        userDao=new UserDao();
        verify=new StringBuilder("");
        uiMessageBox.setModality(Modality.APPLICATION_MODAL);
        selectUserType.getItems().addAll("用户","管理员");
        image.screenToLocal(20,20);

        loginCheck.setFont(new Font("System", 13));
        btnSignIn.setFont(new Font("System",13));
        final Tooltip tooltip = new Tooltip();
        tooltip.setText(
                "\nYour password must be\n" +
                        "at least 8 characters in length\n"
        );
        ImageView tipImage=new ImageView(new Image(String.valueOf(((UiLogin.class.getResource("/png/icons8-close_window.png"))))));
        tooltip.setGraphic(tipImage);
        loginPassword.setTooltip(tooltip);
        selectUserType.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                   verify=new StringBuilder(selectUserType.getValue().toString());
                     System.out.println(verify);
                }
        });

    }

    public void setLoginStage(Stage loginStage){
        this.loginStage=loginStage;
    }

    @FXML
    void LoginCheckEvent(ActionEvent event) {

        if (selectUserType.getValue()==null) {
            new AlertDefined(Alert.AlertType.INFORMATION, "提示", "请选择你的身份信息").show();
            return;
        }
                try {
                    for (User user :userDao.list())
                        if (loginVerify(user)) {
                            loginStage.hide();
                            uiMainFrame.show(user);
                            return;
                        }
                    new AlertDefined(Alert.AlertType.ERROR,"提示","你的账号或密码错误").show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
    }

    @FXML
    void LoginExitEvent(ActionEvent event){
        Platform.exit();
    }

    @FXML
    void loginCheckMinimize(ActionEvent event){
        loginStage.setIconified(true);
    }

    @FXML
    void loginSignIn(ActionEvent event) throws Exception {
        new UiSignIn().start(signInStage);
    }

    private  Boolean loginVerify(User user){

        return (loginID.getText().equals(user.getId())&&
                MD5.encrypt(loginPassword.getText()).equals(user.getPassword())&&verify.toString().equals(user.getUserType()))? true:false;
    }

}
