/**
 *
 * CakePHP Eclipse Plugin
 * Copyright 2012, DoApp, Inc. (http://www.doapps.com)
 *
 * Licensed under The MIT License
 * Redistributions of files must retain the above copyright notice.
 *
 * @copyright     Copyright 2012, DoApp, Inc. (http://www.doapps.com)
 * @link          https://github.com/doapp/cakephp-eclipse-plugin
 * @license       MIT License (http://www.opensource.org/licenses/mit-license.php)
 */
package com.doapps.cakephp.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.doapps.cakephp.Activator;
import com.doapps.cakephp.files.CakeVersion;

/**
 * This class represents a preference page that
 * is contributed to the Preferences dialog. By 
 * subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows
 * us to create a page that is small and knows how to 
 * save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They
 * are stored in the preference store that belongs to
 * the main plug-in class. That way, preferences can
 * be accessed directly via the preference store.
 */

public class CakePHPPreferencePage
	extends FieldEditorPreferencePage
	implements IWorkbenchPreferencePage {

	public CakePHPPreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("CakePHP preferences:");
	}
	
	/**
	 * Creates the field editors. Field editors are abstractions of
	 * the common GUI blocks needed to manipulate various types
	 * of preferences. Each field editor knows how to save and
	 * restore itself.
	 */
	public void createFieldEditors() {
		//Setup a key value in format: {{"2.1","2.1"},{"1.3","1.3"}}	
		String[][] tmpVersionsKeyValueList = new String[CakeVersion.getVersions().size()][2];
				
		int i=0;
		for (String s : CakeVersion.getVersions()) {
			tmpVersionsKeyValueList[i][0] = tmpVersionsKeyValueList[i][1] = s;
			i++;
		}
		
		CakeVersion.getVersions();
		addField(new ComboFieldEditor(PreferenceConstants.P_CAKE_VER,
				"&CakePHP Version", tmpVersionsKeyValueList, getFieldEditorParent()));

		addField(new StringFieldEditor(PreferenceConstants.P_APP_DIR,
				"&App folder (relative to project):", getFieldEditorParent()));

		addField(new BooleanFieldEditor(PreferenceConstants.P_CREATE_FILES_AUTOMATICALLY,
        "&Automatically Create Files (if they don't exist):", getFieldEditorParent()));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}
	
}