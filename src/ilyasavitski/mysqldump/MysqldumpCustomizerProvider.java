/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ilyasavitski.mysqldump;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectUtils;
import org.netbeans.spi.project.ui.support.ProjectCustomizer;
import org.netbeans.spi.project.ui.support.ProjectCustomizer.Category;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;

/**
 *
 * @author ilyasavitski
 */
public class MysqldumpCustomizerProvider
        implements ProjectCustomizer.CompositeCategoryProvider, ChangeListener {

    private final String name;
    private MysqldumpCustomizerPanel panel;
    private Lookup context;
    private Category category;
    private Project project;

    private MysqldumpCustomizerProvider(String name) {
        this.name = name;
    }

    @Override
    public Category createCategory(Lookup context) {
        return ProjectCustomizer.Category.create(name, name, null);
    }

    @Override
    public JComponent createComponent(Category category, Lookup context) {

        this.category = category;
        this.context = context;

        category.setOkButtonListener(new OkButtonActionListener());
        MysqldumpCustomizerPanel panel = getPanel();
        
        return panel;
    }

    private MysqldumpCustomizerPanel getPanel() {
        
        assert context != null;
        Project currentProject = context.lookup(Project.class);
        
        if (panel == null || project != currentProject) {
            project = currentProject;
            panel = new MysqldumpCustomizerPanel(project);
            panel.addChangeListener(this);
            panel.load();
        }
        
        return panel;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        validate();
    }
    
    private void validate() { 
        category.setErrorMessage(getPanel().getErrorMessage()); 
        category.setValid(getPanel().valid()); 
    }

    private class OkButtonActionListener implements ActionListener {

        public OkButtonActionListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!category.isValid()) {
                return;
            }
            getPanel().save();
        }
    }

    @NbBundle.Messages({"LBL_Config=Mysqldump"})
    @ProjectCustomizer.CompositeCategoryProvider.Registration(
            projectType = "org-netbeans-modules-php-project",
            position = 10)
    public static MysqldumpCustomizerProvider createMysqldumpCustomizerProvider() {
        return new MysqldumpCustomizerProvider(Bundle.LBL_Config());
    }
}
