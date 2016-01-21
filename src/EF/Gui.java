package EF;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;


public class Gui extends JFrame {
    DbWorker worker = new DbWorker();

    //Main panel front
    JPanel main;
    JButton mainAddRroButton;

    JButton mainAddOwnerButton;
    JButton mainOwnerFiscButton;
    JButton mainInfoRroButton;
    JButton mainExit;

    //Fisc Input RRO panel

    JPanel fisc;
    JButton fiscSaveRroButton;
    JButton fiscCloseButton;
    JButton fiscDelRROButton;

    JComboBox fiscOwnerBox;

    JLabel fiscZnLabel;
    JLabel fiscFnLabel;
    JLabel fiscDateCrLabel;
    JLabel fiscDateActLabel;
    JLabel fiscDateFirstActLabel;
    JLabel fiscModelLabel;
    JLabel fiscPoLabel;
    JLabel fiscOwnerLabel;

    JComboBox fiscZnBox;

    JFormattedTextField fiscFnField;
    JFormattedTextField fiscDateCrField;
    JFormattedTextField fiscDateActField;
    JFormattedTextField fiscDateFirstActField;
    JTextField fiscModelField;
    JTextField fiscPoField;


    //Owner input panel
    JPanel owner;
    JLabel ownerNameLabel;
    JLabel ownerAdresLabel;
    JLabel ownerEdrpoLabel;
    JLabel ownerIpnLabel;

    JComboBox ownerListComboBox;

    JTextField ownerAdresField;
    JTextField ownerEdrpoField;
    JTextField ownerIpnField;

    JButton ownerSaveButton;
    JButton ownerDeleteButton;
    JButton ownerCloseButton;

    //owner&fisc
    JScrollPane ownFisScrollPane;
    JPanel ownFisc;
    JButton ownFiscBackButton;
    JComboBox ownFiscBox;
    JTextArea ownFiscArea;

    //toXlsPanel
    JPanel toXls;
    JButton toXlsBackButton;
    JButton toXlsKassiButton;
    JButton toXlsOwnersButon;


    public Gui(String s) {
        super(s);

        Handler handler = new Handler();

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 420);
        setResizable(false);
        setLocation(300, 100);


        //main panel
        main = new JPanel();
        mainAddRroButton = new JButton("Работа с РРО");
        mainAddRroButton.setBounds(200, 40, 200, 40);

        mainAddOwnerButton = new JButton("Управление владельцами");
        mainAddOwnerButton.setBounds(200, 110, 200, 40);
        mainOwnerFiscButton = new JButton("Кассы владельца");
        mainOwnerFiscButton.setBounds(200, 170, 200, 40);
        mainInfoRroButton = new JButton("Экспорт данных ");
        mainInfoRroButton.setBounds(200, 230, 200, 40);
        mainExit = new JButton("Выход");
        mainExit.setBounds(200, 290, 200, 40);

        main.setLayout(null);

        mainAddRroButton.addActionListener(handler);
        mainAddOwnerButton.addActionListener(handler);
        mainExit.addActionListener(handler);
        mainInfoRroButton.addActionListener(handler);
        mainOwnerFiscButton.addActionListener(handler);

        main.add(mainAddRroButton);
        main.add(mainAddOwnerButton);
        main.add(mainExit);
        main.add(mainInfoRroButton);
        main.add(mainOwnerFiscButton);
        // main.add(mainDelRroButton);
        main.setVisible(true);
        add(main);


        //fisc panel
        fisc = new JPanel();
        fisc.setLayout(null);
        fiscSaveRroButton = new JButton("Сохранить");
        fiscSaveRroButton.setBounds(55, 350, 150, 30);
        fiscCloseButton = new JButton("Вернуться назад");
        fiscCloseButton.setBounds(395, 350, 150, 30);
        fiscDelRROButton = new JButton("Удалить РРО");
        fiscDelRROButton.setBounds(225, 350, 150, 30);

        try {
            fiscOwnerBox = new JComboBox(worker.ownerList().toArray());
            fiscOwnerBox.setBounds(105, 180, 355, 20);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error!!", JOptionPane.ERROR_MESSAGE);
            // e.printStackTrace();
        }

        try {
            fiscZnBox = new JComboBox(worker.fiscList().toArray());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error!!", JOptionPane.ERROR_MESSAGE);
            // e.printStackTrace();
        }

        fiscCloseButton.addActionListener(handler);
        fiscSaveRroButton.addActionListener(handler);
        fiscZnBox.addActionListener(handler);
        fiscDelRROButton.addActionListener(handler);

        fiscZnLabel = new JLabel("Заводской номер :");
        fiscZnLabel.setBounds(30, 20, 110, 20);
        fiscFnLabel = new JLabel("Фискальный номер :");
        fiscFnLabel.setBounds(270, 17, 140, 30);
        fiscDateCrLabel = new JLabel("Дата изготовления :");
        fiscDateCrLabel.setBounds(30, 60, 140, 20);
        fiscDateActLabel = new JLabel("Дата введения в эксплуатацию :");
        fiscDateActLabel.setBounds(30, 220, 250, 20);
        fiscDateFirstActLabel = new JLabel("Дата первичного введения в эксплуатацию :");
        fiscDateFirstActLabel.setBounds(30, 260, 280, 20);
        fiscModelLabel = new JLabel("Модель РРО :");
        fiscModelLabel.setBounds(30, 100, 100, 20);
        fiscPoLabel = new JLabel("Версия програмного обеспечения :");
        fiscPoLabel.setBounds(30, 140, 220, 20);
        fiscOwnerLabel = new JLabel("Владелец :");
        fiscOwnerLabel.setBounds(30, 180, 100, 20);


        fiscZnBox.setEditable(true);
        fiscZnBox.setBounds(150, 20, 110, 20);


        try {
            MaskFormatter fnFormater = new MaskFormatter("**********");
            fnFormater.setValidCharacters("0123456789");
            fiscFnField = new JFormattedTextField(fnFormater);

        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        try {
            MaskFormatter dateFormater = new MaskFormatter("**.**.****");
            fiscDateCrField = new JFormattedTextField(dateFormater);
            dateFormater.setValidCharacters("0123456789");

        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Неправильный формат даты! Введите ##.##.####!", "Error!", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        try {
            MaskFormatter dateFormater = new MaskFormatter("**.**.****");
            fiscDateActField = new JFormattedTextField(dateFormater);
            dateFormater.setValidCharacters("0123456789");

        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Неправильный формат даты! Введите ##.##.####!", "Error!", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        try {
            MaskFormatter dateFormater = new MaskFormatter("**.**.****");
            fiscDateFirstActField = new JFormattedTextField(dateFormater);
            dateFormater.setValidCharacters("0123456789");

        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Неправильный формат даты! Введите ##.##.####!", "Error!", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }


        fiscFnField.setBounds(400, 20, 80, 20);

        fiscDateCrField.setBounds(150, 60, 90, 20);

        fiscDateActField.setBounds(240, 220, 100, 20);

        fiscDateFirstActField.setBounds(295, 260, 100, 20);
        fiscModelField = new JTextField(15);
        fiscModelField.setBounds(115, 100, 100, 20);
        fiscPoField = new JTextField(15);
        fiscPoField.setBounds(240, 140, 220, 20);


        fisc.add(fiscZnLabel);
        fisc.add(fiscZnBox);
        fisc.add(fiscDateCrLabel);
        fisc.add(fiscDateCrField);
        fisc.add(fiscModelLabel);
        fisc.add(fiscModelField);
        fisc.add(fiscPoLabel);
        fisc.add(fiscPoField);
        fisc.add(fiscOwnerLabel);

        fisc.add(fiscFnLabel);
        fisc.add(fiscFnField);
        fisc.add(fiscDateFirstActLabel);
        fisc.add(fiscDateFirstActField);
        fisc.add(fiscDateActLabel);
        fisc.add(fiscDateActField);

        fisc.add(fiscSaveRroButton);
        fisc.add(fiscCloseButton);
        fisc.add(fiscOwnerBox);
        fisc.add(fiscDelRROButton);
        fisc.setVisible(true);


        //owner input
        owner = new JPanel();
        owner.setLayout(null);

        ownerNameLabel = new JLabel("Наименование владельца :");
        ownerNameLabel.setBounds(30, 30, 170, 20);
        ownerAdresLabel = new JLabel("Адрес :");
        ownerAdresLabel.setBounds(30, 60, 100, 20);
        ownerEdrpoLabel = new JLabel("ЕДРПО :");
        ownerEdrpoLabel.setBounds(30, 90, 100, 20);
        ownerIpnLabel = new JLabel("Индивидуальный налоговый номер :");
        ownerIpnLabel.setBounds(30, 120, 230, 20);

        try {
            ownerListComboBox = new JComboBox(worker.ownerList().toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ownerListComboBox.setBounds(205, 30, 250, 20);
        ownerListComboBox.setEditable(true);
        ownerAdresField = new JTextField(70);
        ownerAdresField.setBounds(100, 60, 250, 20);
        ownerEdrpoField = new JTextField(30);


        ownerEdrpoField.setBounds(100, 90, 250, 20);
        ownerIpnField = new JTextField(30);


        ownerIpnField.setBounds(250, 120, 250, 20);

        ownerSaveButton = new JButton("Сохранить");
        ownerSaveButton.setBounds(55, 350, 150, 30);
        ownerCloseButton = new JButton("Вернуться назад");
        ownerCloseButton.setBounds(395, 350, 150, 30);
        ownerDeleteButton = new JButton("Удалить владельца");
        ownerDeleteButton.setBounds(220, 350, 160, 30);


        ownerSaveButton.addActionListener(handler);
        ownerCloseButton.addActionListener(handler);
        ownerListComboBox.addActionListener(handler);
        ownerDeleteButton.addActionListener(handler);


        owner.add(ownerNameLabel);
        owner.add(ownerDeleteButton);
        owner.add(ownerListComboBox);
        owner.add(ownerAdresLabel);
        owner.add(ownerAdresField);
        owner.add(ownerEdrpoLabel);
        owner.add(ownerEdrpoField);
        owner.add(ownerIpnLabel);
        owner.add(ownerIpnField);
        owner.add(ownerSaveButton);
        owner.add(ownerCloseButton);
        owner.setVisible(true);

        //owner&fisc
        ownFisc = new JPanel();
        ownFisc.setLayout(null);
        ownFiscBackButton = new JButton("Вернуться назад");
        ownFiscBackButton.setBounds(340, 335, 150, 40);
        try {
            ownFiscBox = new JComboBox(worker.ownerList().toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ownFiscBox.setBounds(30, 30, 300, 20);
        ownFiscArea = new JTextArea();
        ownFisScrollPane = new JScrollPane(ownFiscArea);
        ownFisScrollPane.setBounds(30, 60, 300, 270);

        ownFiscBackButton.addActionListener(handler);
        ownFiscBox.addActionListener(handler);

        ownFisc.add(ownFisScrollPane);
        ownFisc.add(ownFiscBackButton);
        ownFisc.add(ownFiscBox);
        ownFisc.setVisible(true);


        //toXlsPanel
        ImageIcon iconXls=new ImageIcon(String.valueOf(new File("resources/icons/xls.png")));
        toXls = new JPanel();
        toXls.setLayout(null);
        toXlsBackButton = new JButton("Вернуться назад");
        toXlsBackButton.setBounds(330, 300, 200, 40);
        toXlsKassiButton = new JButton(" Экспорт информации о кассах!   ");
        toXlsKassiButton.setIcon(iconXls);
        toXlsKassiButton.setBounds(100, 100, 300, 35);
        toXlsOwnersButon = new JButton("Экспорт информации о владельцах!");
        toXlsOwnersButon.setIcon(iconXls);
        toXlsOwnersButon.setBounds(100, 180, 300, 35);
        toXls.add(toXlsBackButton);
        toXls.add(toXlsKassiButton);
        toXls.add(toXlsOwnersButon);

        toXlsOwnersButon.addActionListener(handler);
        toXlsKassiButton.addActionListener(handler);
        toXlsBackButton.addActionListener(handler);

        toXls.setVisible(true);


        revalidate();
        repaint();


        //add(fisc);
        //fisc.setVisible(true);

        //main.setVisible(true);
        //main.repaint();

    }

    public class Handler implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == mainAddOwnerButton) {
                ownerListComboBox.setSelectedItem("");
                remove(main);
                add(owner);
                revalidate();
                repaint();

            } else if (e.getSource() == mainAddRroButton) {
                remove(main);
                add(fisc);
                fiscZnBox.setSelectedItem("");
                fiscFnField.setText("");
                fiscDateActField.setText("");
                fiscDateCrField.setText("");
                fiscDateFirstActField.setText("");
                fiscModelField.setText("");
                fiscPoField.setText("");
                revalidate();
                repaint();
            } else if (e.getSource() == fiscSaveRroButton) {
                if (fiscFnField.getText().equals("          ")) {
                    JOptionPane.showMessageDialog(null, "Не указан фискальный номер!", "Warning!", JOptionPane.WARNING_MESSAGE);
                } else if (fiscZnBox.getSelectedItem().toString().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Не указан заводской номер!", "Warning!", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        worker.addNewFiscal(fiscZnBox.getSelectedItem().toString(), fiscFnField.getText(), fiscDateCrField.getText(), fiscDateActField.getText(), fiscOwnerBox.getSelectedItem().toString(), fiscModelField.getText(), fiscPoField.getText(), fiscDateFirstActField.getText());


                        if (worker.fiscCount() > fiscZnBox.getItemCount()) {
                            fiscZnBox.addItem(fiscZnBox.getSelectedItem().toString());
                        }
                        JOptionPane.showMessageDialog(null, "Данные успешно внесены!", "Information!", JOptionPane.INFORMATION_MESSAGE);


                    } catch (SQLException e1) {
                        JOptionPane.showMessageDialog(null, e1.getMessage(), "Warning!", JOptionPane.WARNING_MESSAGE);

                    }
                }


            } else if (e.getSource() == fiscCloseButton) {
                remove(fisc);
                add(main);
                revalidate();
                repaint();
            } else if (e.getSource() == ownerSaveButton) {





                    if ((ownerListComboBox.getSelectedItem().toString().equals("")) || (ownerAdresField.getText().equals("")) || (ownerEdrpoField.getText().equals(""))) {
                        JOptionPane.showMessageDialog(null, "Не все обязательные поля заполнены", "Warning!", JOptionPane.WARNING_MESSAGE);

                    } else {
                        try {

                            if (ownerIpnField.getText().equals("")) {
                                worker.addNewOwner(ownerListComboBox.getSelectedItem().toString(), ownerAdresField.getText(), ownerEdrpoField.getText());
                            } else {
                                try{

                                    Integer.parseInt(ownerIpnField.getText());
                                worker.addNewOwner(ownerListComboBox.getSelectedItem().toString(), ownerAdresField.getText(), ownerEdrpoField.getText(), ownerIpnField.getText());
                                if (worker.ownersCount() > ownerListComboBox.getItemCount()) {
                                    fiscOwnerBox.addItem(ownerListComboBox.getSelectedItem());
                                    ownerListComboBox.addItem(ownerListComboBox.getSelectedItem());
                                    ownFiscBox.addItem(ownerListComboBox.getSelectedItem());
                                }
                                JOptionPane.showMessageDialog(null, "Данные успешно внесены!", "Information!", JOptionPane.INFORMATION_MESSAGE);
                                } catch (Exception e2){
                                    JOptionPane.showMessageDialog(null, e2.getMessage(), "Error!!", JOptionPane.ERROR_MESSAGE);
                                }
                            }




                        } catch (SQLException e1) {
                            JOptionPane.showMessageDialog(null, e1.getMessage(), "Error!!", JOptionPane.ERROR_MESSAGE);
                            // e1.printStackTrace();
                        }
                    }



            } else if (e.getSource() == ownerListComboBox) {
                try {
                    if (!worker.sqlAskOwners("edrpo", ownerListComboBox.getSelectedItem().toString()).equals("")) {

                        ownerEdrpoField.setText(worker.sqlAskOwners("edrpo", ownerListComboBox.getSelectedItem().toString()));
                        ownerAdresField.setText(worker.sqlAskOwners("adres", ownerListComboBox.getSelectedItem().toString()));
                        ownerIpnField.setText(worker.sqlAskOwners("IPN", ownerListComboBox.getSelectedItem().toString()));


                    } else {

                        ownerAdresField.setText("");
                        ownerEdrpoField.setText("");
                        ownerIpnField.setText("");
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            } else if (e.getSource() == ownerCloseButton) {
                ownerAdresField.setText("");
                ownerEdrpoField.setText("");
                ownerIpnField.setText("");
                remove(owner);
                add(main);
                revalidate();
                repaint();
            } else if (e.getSource() == fiscZnBox) {
                try {
                    if (!worker.sqlAskKassi("fiscNum", fiscZnBox.getSelectedItem().toString()).equals("")) {
                        // JOptionPane.showMessageDialog(null, "Этот аппарат уже зарегистриррован!", "Warning!", JOptionPane.PLAIN_MESSAGE);
                        fiscFnField.setText(worker.sqlAskKassi("fiscNum", fiscZnBox.getSelectedItem().toString()));
                        fiscDateActField.setText(worker.sqlAskKassi("dateActive", fiscZnBox.getSelectedItem().toString()));
                        fiscDateCrField.setText(worker.sqlAskKassi("dateCreate", fiscZnBox.getSelectedItem().toString()));
                        fiscDateFirstActField.setText(worker.sqlAskKassi("dateFirstActive", fiscZnBox.getSelectedItem().toString()));
                        fiscModelField.setText(worker.sqlAskKassi("model", fiscZnBox.getSelectedItem().toString()));
                        fiscPoField.setText(worker.sqlAskKassi("modelPO", fiscZnBox.getSelectedItem().toString()));
                        fiscOwnerBox.setSelectedItem(worker.sqlAskKassi("ownerName", fiscZnBox.getSelectedItem().toString()));
                    } else {

                        fiscFnField.setText("");
                        fiscDateActField.setText("");
                        fiscDateCrField.setText("");
                        fiscDateFirstActField.setText("");
                        fiscModelField.setText("");
                        fiscPoField.setText("");
                    }
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "Error!!", JOptionPane.ERROR_MESSAGE);
                    // e1.printStackTrace();
                }

            } else if (e.getSource() == fiscDelRROButton) {
                try {
                    int reply = JOptionPane.showConfirmDialog(null, "Вы уверены, что хотите удалить фискальный регистратор " +
                            fiscZnBox.getSelectedItem().toString() + " из базы..? ", "Warning", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        worker.deleteFisc(fiscZnBox.getSelectedItem().toString());
                        fiscZnBox.removeItem(fiscZnBox.getSelectedItem().toString());
                    }

                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "Error!!", JOptionPane.ERROR_MESSAGE);
                    // e1.printStackTrace();
                }
            } else if (e.getSource() == ownerDeleteButton) {
                try {
                    if (worker.ownersHadKass(ownerListComboBox.getSelectedItem().toString()) > 0) {
                        JOptionPane.showMessageDialog(null, "Вы не можете удалить данного владельца! \n Количество принадлежащих ему касс : " + worker.ownersHadKass(ownerListComboBox.getSelectedItem().toString()));
                    } else {
                        int reply = JOptionPane.showConfirmDialog(null, "Вы уверены, что хотите удалить владельца: " +
                                ownerListComboBox.getSelectedItem().toString() + " из базы..? ", "Warning", JOptionPane.YES_NO_OPTION);
                        if (reply == JOptionPane.YES_OPTION) {
                            worker.delOwner(ownerListComboBox.getSelectedItem().toString());
                            ownerListComboBox.removeItem(ownerListComboBox.getSelectedItem().toString());
                            fiscOwnerBox.removeItem(ownerListComboBox.getSelectedItem().toString());
                            ownFiscBox.removeItem(ownerListComboBox.getSelectedItem().toString());
                        }

                    }
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "Error!!", JOptionPane.ERROR_MESSAGE);
                    // e1.printStackTrace();
                }
            } else if (e.getSource() == mainOwnerFiscButton) {
                remove(main);
                add(ownFisc);
                revalidate();
                repaint();
            } else if (e.getSource() == ownFiscBackButton) {
                remove(ownFisc);
                add(main);
                revalidate();
                repaint();
            } else if (e.getSource() == ownFiscBox) {
                try {
                    ownFiscArea.setText("");
                    for (String q : (worker.sqlAskKassiByOwners(ownFiscBox.getSelectedItem().toString()))) {
                        ownFiscArea.append(q + "\n");
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            } else if (e.getSource() == mainInfoRroButton) {
                remove(main);
                add(toXls);
                revalidate();
                repaint();
            } else if (e.getSource() == toXlsBackButton) {
                remove(toXls);
                add(main);
                revalidate();
                repaint();
            } else if (e.getSource() == toXlsOwnersButon) {
                String name = "";
                String dir = "";
                JFileChooser c = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("xls", "xls");
                c.setFileFilter(filter);
                c.setSelectedFile(new File("C:\\owners.xls"));
                int rVal = c.showSaveDialog(null);
                if (rVal == JFileChooser.APPROVE_OPTION) {
                    name = c.getSelectedFile().getName();
                    dir = c.getCurrentDirectory().toString();
                    try {
                        worker.generateXls("owners", dir, name);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

            } else if (e.getSource() == toXlsKassiButton) {
                String name = "";
                String dir = "";
                JFileChooser c = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("xls", "xls");
                c.setFileFilter(filter);
                c.setSelectedFile(new File("C:\\fiscal.xls"));
                int rVal = c.showSaveDialog(null);
                if (rVal == JFileChooser.APPROVE_OPTION) {
                    name = c.getSelectedFile().getName();
                    dir = c.getCurrentDirectory().toString();
                    try {
                        worker.generateXls("kassi", dir, name);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

            } else if (e.getSource() == mainExit) {
                int reply = JOptionPane.showConfirmDialog(null, "Вы хотите закрыть EasyFiscal..?", "Close", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    Gui.this.dispose();
                    worker.closeConection();
                }

            }
        }


    }
}



