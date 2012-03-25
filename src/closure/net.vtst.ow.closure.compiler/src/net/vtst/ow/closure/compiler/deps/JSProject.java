package net.vtst.ow.closure.compiler.deps;

import java.util.List;

import com.google.javascript.jscomp.AbstractCompiler;
import com.google.javascript.jscomp.deps.SortedDependencies.CircularDependencyException;

/**
 * Concrete implementation of a compilation set, which may own some compilation unit, and contain
 * some delegated compilation sets.
 * <br>
 * <b>Thread safety:</b>  This class is not thread safe.  Only the sub-class {@code AbstractJSProject}
 * needs to be thread safe.
 * @author Vincent Simonet
 */
public class JSProject extends AbstractJSProject {

  public <T extends JSUnit> void setUnits(AbstractCompiler compiler, List<T> units) throws CircularDependencyException {
    for (JSUnit unit: units) {
      unit.updateDependencies(compiler);
    }
    super.setUnits(compiler, units);
  }
  
  // **************************************************************************
  // Referenced projects

  private List<AbstractJSProject> referencedProjects;
  
  /**
   * Set the projects referenced by a project.
   * @param compilationSet  The referenced projects.
   */
  public void setReferencedProjects(List<AbstractJSProject> referencedProjects) {
    this.referencedProjects = referencedProjects;
  }
  
  /* (non-Javadoc)
   * @see net.vtst.ow.closure.compiler.deps.AbstractJSProject#getReferencedProjects()
   */
  @Override
  protected List<AbstractJSProject> getReferencedProjects() {
    return referencedProjects;
  }
}