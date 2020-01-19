Name:          uom-systems
Version:       0.7
Release:       1%{?dist}
Summary:       Units of Measurement Systems (JSR 363)
License:       BSD
URL:           https://github.com/unitsofmeasurement/%{name}
Source0:       https://github.com/unitsofmeasurement/%{name}/archive/%{version}/%{name}-%{version}.tar.gz

BuildRequires: junit
BuildRequires: maven-local
BuildRequires: maven-jar-plugin
BuildRequires: maven-install-plugin
BuildRequires: maven-dependency-plugin
BuildRequires: mvn(si.uom:si-parent:pom:)
BuildRequires: mvn(si.uom:si-units-java8)
BuildRequires: mvn(javax.measure:unit-api)
BuildRequires: mvn(tec.uom:uom-parent:pom:)
BuildRequires: mvn(tec.uom:uom-se)

BuildArch:     noarch

%description
Units of Measurement Systems - modules for JSR 363.

%package javadoc
BuildArch: noarch
Summary: Javadoc for the Units of Measurement Systems

%description javadoc
This package contains documentation for the Units of Measurement
Systems (JSR 363).

%prep
%setup -q -n %{name}-%{version}
%pom_disable_module common	# use only Java 8+
%pom_disable_module unicode	# use only Java 8+
%pom_xpath_remove "pom:properties/pom:quantity.version"
%pom_xpath_inject "pom:properties" "<quantity.version>%{version}</quantity.version>"

%build
%mvn_build

%install
%mvn_install

%files -f .mfiles
%doc README.md
%license LICENSE

%files javadoc -f .mfiles-javadoc
%license LICENSE

%changelog
* Wed Apr 12 2017 Nathan Scott <nathans@redhat.com> - 0.7-1
- Update to latest upstream sources.

* Tue Apr 11 2017 Dave Brolley <brolley@redhat.com> - 0.6-3
- Spec file changes for building on RHEL7.

* Wed Mar 22 2017 Nathan Scott <nathans@redhat.com> - 0.6-2
- Incorprate feedback from gil cattaneo on all uom packages.

* Mon Mar 06 2017 Nathan Scott <nathans@redhat.com> - 0.6-1
- Update to latest upstream sources.

* Tue Feb 28 2017 Nathan Scott <nathans@redhat.com> - 0.5-3
- Resolve lintian errors - source, license, documentation.

* Fri Feb 24 2017 Nathan Scott <nathans@redhat.com> - 0.5-2
- Add unitsofmeasurement prefix to package name.

* Fri Oct 14 2016 Nathan Scott <nathans@redhat.com> - 0.5-1
- Initial version.
