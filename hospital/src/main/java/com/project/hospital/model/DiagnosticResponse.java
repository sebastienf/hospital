package com.project.hospital.model;

import java.util.List;
import java.util.Objects;

import com.project.hospital.model.enumeration.PathologyType;

/**
 * Diagnostic service response 
 *
 */
public class DiagnosticResponse {
	
	/**
	 * Pathologies' list
	 */
	private List<PathologyType> pathologies;

	public DiagnosticResponse() {
	}
	
	public DiagnosticResponse(List<PathologyType> pathologies) {
		this.pathologies = pathologies;
	}
	
	public List<PathologyType> getPathologies() {
		return pathologies;
	}

	public void setPathologies(List<PathologyType> pathologies) {
		this.pathologies = pathologies;
	}
	
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiagnosticResponse)) return false;
        DiagnosticResponse that = (DiagnosticResponse) o;
        return Objects.equals(pathologies, that.pathologies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pathologies);
    }
	
	
}
