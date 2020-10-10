package radio;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import org.junit.Test;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.dependencies.SliceRule;
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;

public class TestArchitecture {

 private static final String ROOT_PACKAGE = "radio";
 private static final String PKG_MODEL = "radio.model";
 private static final String PKG_PORTS = "radio.model.ports";
 private static final String PKG_ADAPTER = "radio.adapter..";

 @Test
 public void testLayers() {
  layeredArchitecture()
    .layer("Adapter").definedBy(PKG_ADAPTER)
    .layer("Ports").definedBy(PKG_PORTS)
    .layer("BusinessLogic").definedBy(PKG_MODEL)
    .whereLayer("Adapter").mayNotBeAccessedByAnyLayer()
    .whereLayer("BusinessLogic").mayOnlyBeAccessedByLayers("Ports")
    .check(new ClassFileImporter().importPackages(ROOT_PACKAGE));
 }
 
 @Test
 public void modelShouldOnlyDependOnPortsOrJdk() {
  JavaClasses jc = new ClassFileImporter().importPackages(ROOT_PACKAGE);

  ArchRule r1 = classes().that().resideInAPackage(PKG_MODEL).should()
    .onlyDependOnClassesThat()
    .resideInAnyPackage(PKG_MODEL, PKG_PORTS, "java..");
  r1.check(jc);
 }

 @Test
 public void portsShouldOnlyDependOnPortsOrJdk() {
  JavaClasses jc = new ClassFileImporter().importPackages(ROOT_PACKAGE);

  ArchRule r1 = classes().that().resideInAPackage(PKG_PORTS).should()
    .onlyDependOnClassesThat()
    .resideInAnyPackage(PKG_PORTS, "java..");
  r1.check(jc);
 }

 @Test
 public void adaptersShouldOnlyDependOnPorts() {
  JavaClasses jc = new ClassFileImporter().importPackages(ROOT_PACKAGE);

  ArchRule r1 = classes().that().resideInAPackage(PKG_ADAPTER).should()
    .onlyDependOnClassesThat()
    .resideInAnyPackage(PKG_ADAPTER, PKG_PORTS, "java..", "javax..");
  r1.check(jc);
 }

 @Test
 public void adaptersShouldNotDependOnEachOther() {
  JavaClasses jc = new ClassFileImporter().importPackages(ROOT_PACKAGE);

  SliceRule rule = SlicesRuleDefinition.slices()
     .matching(PKG_ADAPTER + "(**)")
     .should().notDependOnEachOther();
   rule.check(jc);
 }
}
