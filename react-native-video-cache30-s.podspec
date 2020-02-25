require "json"

package = JSON.parse(File.read(File.join(__dir__, "package.json")))

Pod::Spec.new do |s|
  s.name         = "react-native-video-cache30-s"
  s.version      = package["version"]
  s.summary      = package["description"]
  s.description  = <<-DESC
                  react-native-video-cache30-s
                   DESC
  s.homepage     = "https://github.com/vunguyen9404/react-native-video-cache30-s"
  s.license      = "MIT"
  # s.license    = { :type => "MIT", :file => "FILE_LICENSE" }
  s.authors      = { "Nguyễn Vũ" => "vunv@30shine.com" }
  s.platforms    = { :ios => "9.0" }
  s.source       = { :git => "https://github.com/vunguyen9404/react-native-video-cache30-s.git", :tag => "#{s.version}" }

  s.source_files = "ios/**/*.{h,m,swift}"
  s.requires_arc = true

  s.dependency "React"
  # ...
  # s.dependency "..."
end

