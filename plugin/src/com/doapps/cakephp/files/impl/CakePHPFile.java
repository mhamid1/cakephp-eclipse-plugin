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
package com.doapps.cakephp.files.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;

import com.doapps.cakephp.files.ICakePHPFile;
import com.doapps.cakephp.files.ICakePHPProject;

public abstract class CakePHPFile implements ICakePHPFile
{
  private ICakePHPProject project;
  private IFile file;
  private static final Pattern NAME_PATTERN = Pattern.compile("(.*)\\..*");
  
  
  public CakePHPFile(ICakePHPProject project, IFile file)
  {
    this.project = project;
    this.file = file;
  }
  
  @Override
  public ICakePHPProject getProject()
  {
    return this.project;
  }
  
  @Override
  public IFile getFile()
  {
    return this.file;
  }

  @Override
  public Pattern getNamePattern()
  {
    return NAME_PATTERN;
  }
  
  @Override
  public String getName()
  {
    Matcher matcher = getNamePattern().matcher(this.file.getName());
    
    try
    {
      if (matcher.find() && (matcher.groupCount() > 0))
      {
        return matcher.group(1);
      }
    }
    catch (Exception e)
    {
      // ignore file names that don't match
    }
    // just return file name, it doesn't have an extension
    return this.file.getName();
  }
  
}
