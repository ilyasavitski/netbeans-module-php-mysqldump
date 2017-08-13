/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ilyasavitski.mysqldump;

import ilyasavitski.mysqldump.utils.StringUtils;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.netbeans.api.project.Project;
import org.openide.util.ChangeSupport;

/**
 *
 * @author s4va
 */
public class MysqldumpCustomizerPanel extends javax.swing.JPanel {

    private final Project project;
    private String errorMessage;
    private boolean isValid;
    private final ChangeSupport changeSuport = new ChangeSupport(this);

    /**
     * Creates new form MysqldumpPanel
     */
    public MysqldumpCustomizerPanel(Project project) {
        this.project = project;
        initComponents();
        init();
    }

    private void init() {
        databaseHost.getDocument().addDocumentListener(new DefaultDocumentListener());
        databaseName.getDocument().addDocumentListener(new DefaultDocumentListener());
        databaseUser.getDocument().addDocumentListener(new DefaultDocumentListener());
        databasePassword.getDocument().addDocumentListener(new DefaultDocumentListener());
    }

    public void load() {
        databaseHost.setText(MysqldumpPreferences.getDatabaseHost(project));
        databaseName.setText(MysqldumpPreferences.getDatabaseName(project));
        databaseUser.setText(MysqldumpPreferences.getDatabaseUser(project));
        databasePassword.setText(MysqldumpPreferences.getDatabasePassword(project));
    }

    public void save() {
        MysqldumpPreferences.setDatabaseHost(project, getDatabaseHost());
        MysqldumpPreferences.setDatabaseName(project, getDatabaseName());
        MysqldumpPreferences.setDatabaseUser(project, getDatabaseUser());
        MysqldumpPreferences.setDatabasePassword(project, getDatabasePassword());
    }

    public void validateFields() {
        isValid = false;
        
//        String databaseHost = getDatabaseHost();
//        if (StringUtils.isEmpty(databaseHost)) {
//            isValid = false;
//            errorMessage = "Database Host can not be empty!";
//            return;
//        }
//        
//        String databaseName = getDatabaseName();
//        if (StringUtils.isEmpty(databaseName)) {
//            isValid = false;
//            errorMessage = "Database Name can not be empty!";
//            return;
//        }

        isValid = true;
        errorMessage = ""; // NOI18N 
    }

    public String getErrorMessage() {
        validateFields();
        return errorMessage;
    }

    public boolean valid() {
        validateFields();
        return isValid;
    }

    public void addChangeListener(ChangeListener listener) {
        changeSuport.addChangeListener(listener);
    }

    public void removeChangeListener(ChangeListener listener) {
        changeSuport.removeChangeListener(listener);
    }

    private void fireChange() {
        changeSuport.fireChange();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        databaseName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        databaseUser = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        databasePassword = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        databaseHost = new javax.swing.JTextField();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(MysqldumpCustomizerPanel.class, "MysqldumpCustomizerPanel.jLabel1.text")); // NOI18N

        databaseName.setText(org.openide.util.NbBundle.getMessage(MysqldumpCustomizerPanel.class, "MysqldumpCustomizerPanel.databaseName.text")); // NOI18N
        databaseName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                databaseNameActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(MysqldumpCustomizerPanel.class, "MysqldumpCustomizerPanel.jLabel2.text")); // NOI18N

        databaseUser.setText(org.openide.util.NbBundle.getMessage(MysqldumpCustomizerPanel.class, "MysqldumpCustomizerPanel.databaseUser.text")); // NOI18N
        databaseUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                databaseUserActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(MysqldumpCustomizerPanel.class, "MysqldumpCustomizerPanel.jLabel3.text")); // NOI18N

        databasePassword.setText(org.openide.util.NbBundle.getMessage(MysqldumpCustomizerPanel.class, "MysqldumpCustomizerPanel.databasePassword.text")); // NOI18N
        databasePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                databasePasswordActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(MysqldumpCustomizerPanel.class, "MysqldumpCustomizerPanel.jLabel4.text")); // NOI18N

        databaseHost.setText(org.openide.util.NbBundle.getMessage(MysqldumpCustomizerPanel.class, "MysqldumpCustomizerPanel.databaseHost.text")); // NOI18N
        databaseHost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                databaseHostActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(databaseHost, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(databaseName, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                        .addComponent(databaseUser)
                        .addComponent(databasePassword)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(databaseHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(databaseName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(databaseUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(databasePassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void databaseNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_databaseNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_databaseNameActionPerformed

    private void databaseUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_databaseUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_databaseUserActionPerformed

    private void databasePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_databasePasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_databasePasswordActionPerformed

    private void databaseHostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_databaseHostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_databaseHostActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField databaseHost;
    private javax.swing.JTextField databaseName;
    private javax.swing.JTextField databasePassword;
    private javax.swing.JTextField databaseUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables

    private String getDatabaseHost() {
        return databaseHost.getText().trim();
    }

    private String getDatabaseName() {
        return databaseName.getText().trim();
    }

    private String getDatabaseUser() {
        return databaseUser.getText().trim();
    }

    private String getDatabasePassword() {
        return databasePassword.getText().trim();
    }

    //~ Inner class 
    private class DefaultDocumentListener implements DocumentListener {

        public DefaultDocumentListener() {
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            processUpdate();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            processUpdate();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            processUpdate();
        }

        private void processUpdate() {
            fireChange();
        }
    }
}