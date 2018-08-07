environments(default: 'SUBRO_POINT_SIT') {

    environment(name: 'SUBRO_POINT_SIT') {
        page(name: "GlobalSearchPage") {
            EventId "56824"
            IncidentId "REF000022"
            EventStatus "Open"
            PatientFirstName "Jonas"
            PatientLastName "Smith"
            PatientID "PID12399"
            PatientDOB "12-13-1989"
            PatientDOBVer "12/13/89"
            MajorClient "Test Major Client 01"
            Client "Test Client 01"
            EmployerGroup "Test C01BG01 EG 01"
        }
        page(name: "GlobalSearchPage") {
            Incident_ReferralFrom ''
        }
    }


}